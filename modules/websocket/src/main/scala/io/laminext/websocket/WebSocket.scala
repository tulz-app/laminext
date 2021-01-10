package io.laminext.websocket

import internal._
import com.raquo.airstream.ownership.Subscription
import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveElement
import org.scalajs.dom
import org.scalajs.dom.raw

import scala.collection.mutable
import scala.concurrent.duration.FiniteDuration
import scala.scalajs.js
import scala.util.control.NonFatal

object WebSocket {

  def url[Receive, Send](url: String): WebSocketReceiveBuilder =
    new WebSocketReceiveBuilder(url)

  def path[Receive, Send](path: String): WebSocketReceiveBuilder = {
    val wsProtocol = if (dom.document.location.protocol == "https:") "wss" else "ws"
    url(s"$wsProtocol://${dom.document.location.host}$path")
  }

}

class WebSocket[Receive, Send](
  url: String,
  initializer: WebSocketInitialize,
  sender: WebSocketSend[Send],
  receiver: WebSocketReceive[Receive],
  managed: Boolean,
  bufferWhenDisconnected: Boolean,
  bufferSize: Int,
  autoReconnect: Boolean,
  reconnectDelay: FiniteDuration,
  reconnectDelayOffline: FiniteDuration,
  reconnectRetries: Int
) {

  private var reconnectRetriesLeft: Int = reconnectRetries

  private var bindsCount                           = 0
  private var maybeWS: js.UndefOr[raw.WebSocket]   = js.undefined
  private val sendBuffer: mutable.ArrayDeque[Send] = mutable.ArrayDeque.empty
  private val eventBus                             = new EventBus[WebSocketEvent[Receive]]()
  private val connectedVar                         = Var(false)
  private val connectingVar                        = Var(false)

  private def initWebSocket(): Unit = {
    if (js.isUndefined(maybeWS)) {
      try {
        connectingVar.writer.onNext(true)
        val ws = new raw.WebSocket(url)
        maybeWS = ws

        initializer(ws)

        ws.onopen = { _ =>
          reconnectRetriesLeft = reconnectRetries
          eventBus.writer.onNext(WebSocketEvent.Connected(ws))
          connectedVar.writer.onNext(true)
          connectingVar.writer.onNext(false)
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
          val willReconnect = managed && event.code != 1000 && autoReconnect && reconnectRetriesLeft > 0 // 1000 – websocket closed normally
          eventBus.writer.onNext(WebSocketEvent.Closed(ws, willReconnect))
          connectedVar.writer.onNext(false)
          connectingVar.writer.onNext(false)
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
    if (bindsCount == 0 && managed) {
      reconnectRetriesLeft = reconnectRetries
      initWebSocket()
    }
    bindsCount += 1
  }

  private def binderStopped(): Unit = {
    bindsCount -= 1
    if (bindsCount == 0 && managed) {
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

  def disconnectNow(): Unit =
    disconnect.onNext(null)

  val disconnect: Observer[Any] = Observer { _ =>
    if (!managed) {
      reconnectRetriesLeft = 0
      stopWebSocket()
    }
  }

  def reconnectNow(): Unit = reconnect.onNext(null)

  val reconnect: Observer[Any] = Observer { _ =>
    if (!managed) {
      reconnectRetriesLeft = reconnectRetries
      initWebSocket()
    }
  }

  def sendOne(message: Send): Unit = {
    send.onNext(message)
  }

  val send: Observer[Send] = Observer(message => {
    sendBuffer.append(message)
    trySend()
  })

  val received: EventStream[Receive] = eventBus.events.collect { case WebSocketEvent.Received(message) => message }

  val connected: EventStream[raw.WebSocket] = eventBus.events.collect { case WebSocketEvent.Connected(ws) => ws }

  val closed: EventStream[(raw.WebSocket, Boolean)] = eventBus.events.collect { case WebSocketEvent.Closed(ws, willReconnect) => (ws, willReconnect) }

  val errors: EventStream[Throwable] = eventBus.events.collect { case WebSocketEvent.Error(error) => error }

  val events: EventStream[WebSocketEvent[Receive]] = eventBus.events

  val isConnected: Signal[Boolean] = connectedVar.signal

  val isConnecting: Signal[Boolean] = connectingVar.signal

}
