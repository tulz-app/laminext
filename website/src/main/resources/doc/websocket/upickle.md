```scala
libraryDependencies += "io.laminext" %%% "websocket-upickle" % "{{laminextVersion}}"
```

The `websocket-upickle` module provides an extension method for the `WebSocketReceiveBuilder`: `.json[Receive, Send]`:

```scala
import io.laminext.fetch.upickle._
import upickle.default._

case class SendData(s: String)

implicit val myWriter: Writer[SendData] = macroW[SendData]

case class ReceiveData(s: String)

implicit val myReader: Reader[ReceiveData] = macroR[ReceiveData]


val ws = WebSocket.url("wss://echo.websocket.org").json[ReceiveData, SendData]
```

[Example](/websocket/example-websocket-echo-upickle)
