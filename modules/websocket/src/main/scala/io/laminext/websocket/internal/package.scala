package io.laminext.websocket

import org.scalajs.dom

package object internal {

  private[websocket] type WebSocketInitialize       = dom.WebSocket => Unit
  private[websocket] type WebSocketSend[Send]       = (dom.WebSocket, Send) => Unit
  private[websocket] type WebSocketReceive[Receive] = dom.MessageEvent => Either[Throwable, Receive]

}
