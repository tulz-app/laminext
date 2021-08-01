package io.laminext.websocket.ziojson

import io.laminext.websocket._
import zio.json._

import scala.util.control.NoStackTrace

class WebSocketReceiveBuilderZioJsonOps(b: WebSocketReceiveBuilder) {

  @inline def json[Receive, Send](implicit receiveDecoder: JsonDecoder[Receive], sendEncoder: JsonEncoder[Send]): WebSocketBuilder[Receive, Send] =
    new WebSocketBuilder[Receive, Send](
      url = b.url,
      protocol = b.protocol,
      initializer = initialize.text,
      sender = send.text[Send](_.toJson),
      receiver = receive.text[Receive](_.fromJson[Receive].left.map(new Exception(_) with NoStackTrace))
    )

}
