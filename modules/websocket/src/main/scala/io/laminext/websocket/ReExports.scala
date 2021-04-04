package io.laminext.websocket

trait ReExports {

  val WebSocket: io.laminext.websocket.WebSocket.type = io.laminext.websocket.WebSocket
  type WebSocket[Receive, Send] = io.laminext.websocket.WebSocket[Receive, Send]
  type WebSocketEvent[Receive]  = io.laminext.websocket.WebSocketEvent[Receive]
  val WebSocketError: io.laminext.websocket.WebSocketError.type = io.laminext.websocket.WebSocketError

}
