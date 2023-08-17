package io.laminext.websocket.upickle

import _root_.upickle.AttributeTagged
import io.laminext.websocket._
import scala.util.Try

class WebSocketReceiveBuilderUpickleOps(b: WebSocketReceiveBuilder) {

  @inline def upickle[T <: AttributeTagged](u: T): UpickleBuilder[T] = new UpickleBuilder[T](u)

  class UpickleBuilder[T <: AttributeTagged](u: T) {
    def json[Receive, Send](implicit receiveReader: u.Reader[Receive], sendWriter: u.Writer[Send]): WebSocketBuilder[Receive, Send] =
      new WebSocketBuilder[Receive, Send](
        url = b.url,
        protocol = b.protocol,
        initializer = initialize.text,
        sender = send.text[Send](u.write(_)),
        receiver = receive.text[Receive](string => Try(u.read[Receive](string)).toEither)
      )
  }

  @inline def json[Receive, Send](implicit
    receiveReader: _root_.upickle.default.Reader[Receive],
    sendWriter: _root_.upickle.default.Writer[Send]
  ): WebSocketBuilder[Receive, Send] =
    new WebSocketBuilder[Receive, Send](
      url = b.url,
      protocol = b.protocol,
      initializer = initialize.text,
      sender = send.text[Send](_root_.upickle.default.write(_)),
      receiver = receive.text[Receive](string => Try(_root_.upickle.default.read[Receive](string)).toEither)
    )

}
