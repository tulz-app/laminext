package io.laminext.websocket

import com.raquo.airstream.ownership.Subscription
import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveElement
import org.scalajs.dom.raw

import scala.collection.mutable
import scala.scalajs.js
import scala.util.control.NonFatal

// TODO: reconnection (flag)
class WebSocket[Receive, Send](
  url: String,
  initialize: WebSocketInitialize,
  send: WebSocketSend[Send],
  receive: WebSocketReceive[Receive],
  bufferWhenDisconnected: Boolean = true
) {

  def dropWhenDisconnected: WebSocket[Receive, Send] =
    new WebSocket[Receive, Send](url, initialize, send, receive, bufferWhenDisconnected = false)

  private var bindsCount                           = 0
  private var maybeWS: js.UndefOr[raw.WebSocket]   = js.undefined
  private val sendBuffer: mutable.ArrayDeque[Send] = mutable.ArrayDeque.empty
  private val eventBus                             = new EventBus[WebSocketEvent[Receive]]()
  private val receivedBus                          = new EventBus[Receive]

  private def initWebSocket(): Unit = {
    try {
      val ws = new raw.WebSocket(url)
      maybeWS = ws

      initialize(ws)

      ws.onopen = { _ =>
        eventBus.writer.onNext(WebSocketEvent.Connected(ws))
      }
      ws.onerror = { event =>
        eventBus.writer.onNext(WebSocketEvent.Error(WebSocketError(event.asInstanceOf[raw.ErrorEvent].message)))
      }
      ws.onmessage = { event =>
        receive(event) match {
          case Right(message) =>
            eventBus.writer.onNext(WebSocketEvent.Message(message))
            receivedBus.writer.onNext(message)
          case Left(error) =>
            eventBus.writer.onNext(WebSocketEvent.Error(error))
            receivedBus.writer.onError(error)
        }
      }
      ws.onclose = { _ =>
        eventBus.writer.onNext(WebSocketEvent.Closed(ws))
      }
    } catch {
      case NonFatal(error) =>
        eventBus.writer.onNext(WebSocketEvent.Error(error))
    }
  }

  private def stopWebSocket(): Unit = {
    maybeWS = js.undefined
  }

  private def binderStarted(): Unit = {
    if (bindsCount == 0) {
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
    if (js.isUndefined(maybeWS) && !bufferWhenDisconnected) {
      sendBuffer.clear()
    }
    maybeWS.foreach { ws =>
      sendBuffer.foreach { message =>
        send(ws, message)
      }
      sendBuffer.clear()
    }
  }

  def send(message: Send): Unit = {
    sendBuffer.append(message)
    trySend()
  }
  def sendObserver: Observer[Send] = Observer(send(_))

  def send[El <: ReactiveElement.Base](messages: Observable[Send]): Binder[El] =
    (element: El) =>
      ReactiveElement.bindSubscription(element) { ctx =>
        binderStarted()
        val subscription = messages.foreach { message =>
          sendBuffer.addOne(message)
          trySend()
        }(ctx.owner)
        new Subscription(
          ctx.owner,
          cleanup = () => {
            binderStopped()
            subscription.kill() // is this necessary?
          }
        )
      }

  def receivedBinder[El <: ReactiveElement.Base](
    onNext: Receive => Unit,
    onError: Throwable => Unit
  ): Binder[El] =
    (element: El) =>
      ReactiveElement.bindSubscription(element) { ctx =>
        binderStarted()
        val subscription = eventBus.events.foreach {
          case WebSocketEvent.Message(message) =>
            onNext(message)
          case WebSocketEvent.Error(error) =>
            onError(error)
          case _ =>
          // nothing
        }(ctx.owner)
        new Subscription(
          ctx.owner,
          cleanup = () => {
            binderStopped()
            subscription.kill() // is this necessary?
          }
        )
      }

  def connectedBinder[El <: ReactiveElement.Base](
    onNext: raw.WebSocket => Unit,
  ): Binder[El] =
    (element: El) =>
      ReactiveElement.bindSubscription(element) { ctx =>
        binderStarted()
        val subscription = eventBus.events.foreach {
          case WebSocketEvent.Connected(ws) =>
            onNext(ws)
          case _ =>
          // nothing
        }(ctx.owner)
        new Subscription(
          ctx.owner,
          cleanup = () => {
            binderStopped()
            subscription.kill() // is this necessary?
          }
        )
      }

  def closedBinder[El <: ReactiveElement.Base](
    onNext: raw.WebSocket => Unit,
  ): Binder[El] =
    (element: El) =>
      ReactiveElement.bindSubscription(element) { ctx =>
        binderStarted()
        val subscription = eventBus.events.foreach {
          case WebSocketEvent.Closed(ws) =>
            onNext(ws)
          case _ =>
          // nothing
        }(ctx.owner)
        new Subscription(
          ctx.owner,
          cleanup = () => {
            binderStopped()
            subscription.kill() // is this necessary?
          }
        )
      }

  def errorBinder[El <: ReactiveElement.Base](
    onNext: Throwable => Unit,
  ): Binder[El] =
    (element: El) =>
      ReactiveElement.bindSubscription(element) { ctx =>
        binderStarted()
        val subscription = eventBus.events.foreach {
          case WebSocketEvent.Error(error) =>
            onNext(error)
          case _ =>
          // nothing
        }(ctx.owner)
        new Subscription(
          ctx.owner,
          cleanup = () => {
            binderStopped()
            subscription.kill() // is this necessary?
          }
        )
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

  def send: SendReceiver[Send] = new SendReceiver[Send](this)

  def received: ReceivedBinders[Receive]   = new ReceivedBinders[Receive](this)
  def connected: ConnectedBinders[Receive] = new ConnectedBinders[Receive](this)
  def closed: ClosedBinders[Receive]       = new ClosedBinders[Receive](this)
  def error: ErrorBinders[Receive]         = new ErrorBinders[Receive](this)

  def receivedStream: EventStream[Receive] = receivedBus.events

}
