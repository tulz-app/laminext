```scala
libraryDependencies += "io.laminext" %%% "websocket-circe" % "{{laminextVersion}}"
```

The `websocket-circe` module provides an extension method for the `WebSocketReceiveBuilder`: `.json[Receive, Send]`:


```scala
import io.circe.generic.JsonCodec
import io.laminext.websocket._

@JsonCodec
case class SendData(s: String)

@JsonCodec
case class ReceiveData(s: String)

val ws = WebSocket.url("wss://echo.websocket.org").json[ReceiveData, SendData]
```
