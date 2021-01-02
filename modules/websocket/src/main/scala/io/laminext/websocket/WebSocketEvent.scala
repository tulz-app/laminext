package io.laminext.websocket

import org.scalajs.dom.raw

sealed abstract private[websocket] class WebSocketEvent[+T]

private[websocket] object WebSocketEvent {

  final case class Connected(ws: raw.WebSocket) extends WebSocketEvent[Nothing]
  final case class Closed(ws: raw.WebSocket)    extends WebSocketEvent[Nothing]
  final case class Error(error: Throwable)      extends WebSocketEvent[Nothing]
  final case class Message[T](message: T)       extends WebSocketEvent[T]

}
