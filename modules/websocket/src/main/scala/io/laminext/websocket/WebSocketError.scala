package io.laminext.websocket

case class WebSocketError(message: String) extends Exception(message)
