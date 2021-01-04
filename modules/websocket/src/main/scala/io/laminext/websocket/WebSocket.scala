package io.laminext.websocket

import com.raquo.airstream.ownership.Subscription
import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveElement
import org.scalajs.dom
import org.scalajs.dom.raw

import scala.collection.mutable
import scala.concurrent.duration.FiniteDuration
import scala.scalajs.js
import scala.util.control.NonFatal
import scala.concurrent.duration._

class WebSocket[Receive, Send](
  url: String,
  initializer: WebSocketInitialize,
  sender: WebSocketSend[Send],
  receiver: WebSocketReceive[Receive],
  bufferWhenDisconnected: Boolean = true,
  bufferSize: Int = 50,
  autoReconnect: Boolean = true,
  reconnectDelay: FiniteDuration = 1.second,
  reconnectDelayOffline: FiniteDuration = 20.second,
  reconnectRetries: Int = 10
) {

  private var reconnectRetriesLeft: Int = reconnectRetries

  def configure(
    bufferWhenDisconnected: Boolean = this.bufferWhenDisconnected,
    bufferSize: Int = this.bufferSize,
    autoReconnect: Boolean = this.autoReconnect,
    reconnectDelay: FiniteDuration = this.reconnectDelay,
    reconnectDelayOffline: FiniteDuration = this.reconnectDelayOffline,
    reconnectRetries: Int = this.reconnectRetries
  ): WebSocket[Receive, Send] =
    new WebSocket[Receive, Send](
      url = url,
      initializer = initializer,
      sender = sender,
      receiver = receiver,
      bufferWhenDisconnected = bufferWhenDisconnected,
      bufferSize = bufferSize,
      autoReconnect = autoReconnect,
      reconnectDelay = reconnectDelay,
      reconnectDelayOffline = reconnectDelayOffline,
      reconnectRetries = reconnectRetries
    )

  private var firstConnection                      = true
  private var bindsCount                           = 0
  private var maybeWS: js.UndefOr[raw.WebSocket]   = js.undefined
  private val sendBuffer: mutable.ArrayDeque[Send] = mutable.ArrayDeque.empty
  private val eventBus                             = new EventBus[WebSocketEvent[Receive]]()
  private val connectedVar                         = Var(false)
  private def initWebSocket(): Unit = {
    if (js.isUndefined(maybeWS)) {
      try {
        val ws = new raw.WebSocket(url)
        maybeWS = ws

        initializer(ws)

        ws.onopen = { _ =>
          reconnectRetriesLeft = reconnectRetries
          eventBus.writer.onNext(WebSocketEvent.Connected(ws, firstConnection))
          firstConnection = false
          connectedVar.writer.onNext(true)
          trySend()
        }
        ws.onerror = { _ =>
          eventBus.writer.onNext(WebSocketEvent.Error(WebSocketError))
        }
        ws.onmessage = { event =>
          receiver(event) match {
            case Right(message) =>
              eventBus.writer.onNext(WebSocketEvent.Received(message))
            case Left(error) =>
              eventBus.writer.onNext(WebSocketEvent.Error(error))
          }
        }
        ws.onclose = { event =>
          maybeWS = js.undefined
          val willReconnect = event.code != 1000 && autoReconnect && reconnectRetriesLeft > 0 // 1000 – websocket closed normally
          eventBus.writer.onNext(WebSocketEvent.Closed(ws, willReconnect))
          connectedVar.writer.onNext(true)
          if (willReconnect) {
            reconnectRetriesLeft = reconnectRetriesLeft - 1
            val delay = if (dom.window.navigator.onLine) {
              reconnectDelay.toMillis.toDouble
            } else {
              reconnectDelayOffline.toMillis.toDouble
            }
            val _ = js.timers.setTimeout(delay) {
              if (bindsCount > 0) {
                initWebSocket()
              }
            }
          }
        }
      } catch {
        case NonFatal(error) =>
          eventBus.writer.onNext(WebSocketEvent.Error(error))
      }
    }
  }

  private def stopWebSocket(): Unit = {
    maybeWS.foreach(_.close())
    maybeWS = js.undefined
  }

  private def binderStarted(): Unit = {
    if (bindsCount == 0) {
      reconnectRetriesLeft = reconnectRetries
      initWebSocket()
    }
    bindsCount += 1
  }

  private def binderStopped(): Unit = {
    bindsCount -= 1
    if (bindsCount == 0) {
      stopWebSocket()
    }
  }

  private def trySend(): Unit = {
    if (js.isUndefined(maybeWS)) {
      if (!bufferWhenDisconnected) {
        sendBuffer.clear()
      } else if (sendBuffer.size > bufferSize) {
        sendBuffer.drop(sendBuffer.size - bufferSize)
      }
    }
    maybeWS.foreach { ws =>
      sendBuffer.foreach { message =>
        sender(ws, message)
      }
      sendBuffer.clear()
    }
  }

  def connect[El <: ReactiveElement.Base]: Binder[El] =
    (element: El) =>
      ReactiveElement.bindSubscription(element) { ctx =>
        binderStarted()
        new Subscription(
          ctx.owner,
          cleanup = () => {
            binderStopped()
          }
        )
      }

  def disconnectNow(): Unit = disconnect.onNext(null)

  val disconnect: Observer[Any] = Observer { _ =>
    reconnectRetriesLeft = 0
    stopWebSocket()
  }

  def reconnectNow(): Unit = reconnect.onNext(null)

  val reconnect: Observer[Any] = Observer { _ =>
    reconnectRetriesLeft = reconnectRetries
    initWebSocket()
  }

  def sendOne(message: Send): Unit = {
    send.onNext(message)
  }

  val send: Observer[Send] = Observer(message => {
    sendBuffer.append(message)
    trySend()
  })

  val received: EventStream[Receive] = eventBus.events.collect { case WebSocketEvent.Received(message) => message }

  val connected: EventStream[(raw.WebSocket, Boolean)] = eventBus.events.collect { case WebSocketEvent.Connected(ws, reconnect) => (ws, reconnect) }

  val closed: EventStream[(raw.WebSocket, Boolean)] = eventBus.events.collect { case WebSocketEvent.Closed(ws, willReconnect) => (ws, willReconnect) }

  val errors: EventStream[Throwable] = eventBus.events.collect { case WebSocketEvent.Error(error) => error }

  val events: EventStream[WebSocketEvent[Receive]] = eventBus.events

  val isConnected: Signal[Boolean] = connectedVar.signal

}
