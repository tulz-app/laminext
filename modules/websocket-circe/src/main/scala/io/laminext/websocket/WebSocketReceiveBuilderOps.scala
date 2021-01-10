package io.laminext.websocket

import io.circe._
import io.circe.parser._

class WebSocketReceiveBuilderOps(b: WebSocketReceiveBuilder) {

  @inline def json[Receive, Send](implicit receiveDecoder: Decoder[Receive], sendEncoder: Encoder[Send]): WebSocketBuilder[Receive, Send] =
    new WebSocketBuilder[Receive, Send](
      url = b.url,
      initializer = initialize.text,
      sender = send.text[Send](sendEncoder(_).noSpaces),
      receiver = receive.text[Receive](decode[Receive])
    )

}
