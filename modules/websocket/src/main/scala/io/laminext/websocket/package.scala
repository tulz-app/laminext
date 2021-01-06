package io.laminext

import org.scalajs.dom
import org.scalajs.dom.raw

package object websocket {

  private[websocket] type WebSocketInitialize       = dom.WebSocket => Unit
  private[websocket] type WebSocketSend[Send]       = (dom.WebSocket, Send) => Unit
  private[websocket] type WebSocketReceive[Receive] = raw.MessageEvent => Either[Throwable, Receive]

}
