package io.laminext.websocket

import org.scalajs.dom

sealed abstract class WebSocketEvent[+Receive]

object WebSocketEvent {

  final case class Connected(ws: dom.WebSocket)                      extends WebSocketEvent[Nothing]
  final case class Closed(ws: dom.WebSocket, willReconnect: Boolean) extends WebSocketEvent[Nothing]
  final case class Error(error: Throwable)                           extends WebSocketEvent[Nothing]
  final case class Received[T](message: T)                           extends WebSocketEvent[T]

}
