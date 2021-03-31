package io.laminext.websocket.zio

import io.laminext.websocket._
import _root_.zio.json._

class WebSocketReceiveBuilderZioOps(b: WebSocketReceiveBuilder) {

  @inline def json[Receive, Send](implicit receiveDecoder: JsonDecoder[Receive], sendEncoder: JsonEncoder[Send]): WebSocketBuilder[Receive, Send] =
    new WebSocketBuilder[Receive, Send](
      url = b.url,
      initializer = initialize.text,
      sender = send.text[Send](sendEncoder.encodeJson(_, Some(0)).toString),
      receiver = receive.text[Receive](receiveDecoder.decodeJson(_).left.map(new Error(_)))
    )

}
