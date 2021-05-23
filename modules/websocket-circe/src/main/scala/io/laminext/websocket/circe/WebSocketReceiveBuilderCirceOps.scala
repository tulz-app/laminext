package io.laminext.websocket.circe

import io.circe._
import io.circe.parser._
import io.laminext.websocket._

class WebSocketReceiveBuilderCirceOps(b: WebSocketReceiveBuilder) {

  @inline def json[Receive, Send](implicit receiveDecoder: Decoder[Receive], sendEncoder: Encoder[Send]): WebSocketBuilder[Receive, Send] =
    new WebSocketBuilder[Receive, Send](
      url = b.url,
      protocol = b.protocol,
      initializer = initialize.text,
      sender = send.text[Send](sendEncoder(_).noSpaces),
      receiver = receive.text[Receive](decode[Receive])
    )

}
