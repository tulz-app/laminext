package io.laminext.websocket.upickle

import upickle.default._
import io.laminext.websocket._

import scala.util.Try

class WebSocketReceiveBuilderUpickleOps(b: WebSocketReceiveBuilder) {

  @inline def json[Receive, Send](implicit receiveReader: Reader[Receive], sendWriter: Writer[Send]): WebSocketBuilder[Receive, Send] =
    new WebSocketBuilder[Receive, Send](
      url = b.url,
      initializer = initialize.text,
      sender = send.text[Send](write(_)),
      receiver = receive.text[Receive](string => Try(read[Receive](string)).toEither)
    )

}
