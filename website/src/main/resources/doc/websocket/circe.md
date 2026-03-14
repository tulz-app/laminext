```scala
libraryDependencies += "dev.laminext" %%% "websocket-circe" % "{{laminextVersion}}"
```

The `websocket-circe` module provides an extension method for the `WebSocketReceiveBuilder`: `.json[Receive, Send]`:

```scala
import io.circe.generic.JsonCodec

@JsonCodec
case class SendData(s: String)

@JsonCodec
case class ReceiveData(s: String)

val ws = WebSocket.url("wss://ws.postman-echo.com/raw").json[ReceiveData, SendData]
```

[Example](/websocket/example-websocket-echo-circe)
