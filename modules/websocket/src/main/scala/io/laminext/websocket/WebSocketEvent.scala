package io.laminext.websocket

import org.scalajs.dom.raw

sealed abstract class WebSocketEvent[+T]

object WebSocketEvent {

  final case class Connected(ws: raw.WebSocket)                      extends WebSocketEvent[Nothing]
  final case class Closed(ws: raw.WebSocket, willReconnect: Boolean) extends WebSocketEvent[Nothing]
  final case class Error(error: Throwable)                           extends WebSocketEvent[Nothing]
  final case class Received[T](message: T)                           extends WebSocketEvent[T]

}
