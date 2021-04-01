```scala
libraryDependencies += "io.laminext" %%% "websocket-zio-json" % "{{laminextVersion}}"
// most likely this as well:
libraryDependencies += "io.github.cquiroz" %%% "scala-java-time" % "2.2.0"
```

The `websocket-zio-json` module provides an extension method for the `WebSocketReceiveBuilder`: `.json[Receive, Send]`:

You will probably need the `scala-java-time` dependency from above, as `zio-json` references some `java.time` classes, 
which are not available in the js runtime by default.

```scala
import zio.json._
import io.laminext.websocket.zio._

case class SendData(s: String)

object SendData {
  implicit val encoder: JsonEncoder[SendData] = DeriveJsonEncoder.gen[SendData]
}

case class ReceiveData(s: String)

object ReceiveData {
  implicit val decoder: JsonDecoder[ReceiveData] = DeriveJsonDecoder.gen[ReceiveData]
}

val ws = WebSocket.url("wss://echo.websocket.org").json[ReceiveData, SendData]
```

[Example](/websocket/example-websocket-echo-zio-json)
