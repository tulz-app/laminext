```scala
libraryDependencies += "io.laminext" %%% "websocket" % "{{laminextVersion}}"
```

```scala
import io.laminext.websocket._
```

In order to build a WebSocket connection, you first need to specify the endpoint to connect to:

## Absolute endpoint URL:

```scala
import io.laminext.websocket._
val ws = WebSocket.url("wss://echo.websocket.org")
```

## Relative endpoint URL:

```scala
import io.laminext.websocket._
val ws = WebSocket.path("/my-ws-endpoint")
```

## Receive

Then you specify the type of the messages to be received:

```scala
import io.laminext.websocket._
WebSocket.path("/my-ws-endpoint").receiveString
WebSocket.path("/my-ws-endpoint").receiveBlob
WebSocket.path("/my-ws-endpoint").receiveArrayBuffer
```

If you want to decode the received messages (JSON, etc), you can specify a decoder:

```scala
import io.laminext.websocket._
case class MyData()
val myDecode: String => Either[Throwable, MyData] = ??? 
WebSocket.path("/my-ws-endpoint").receiveText(myDecode)
```

## Send

After that, in a similar way you specify the type of messages to be sent:

```scala
import io.laminext.websocket._
WebSocket.path("/my-ws-endpoint").receiveString.sendString
WebSocket.path("/my-ws-endpoint").receiveString.sendBlob
WebSocket.path("/my-ws-endpoint").receiveString.sendArrayBuffer
```

If you want to encode the messages before sending (JSON, etc), you can specify an encoder:

```scala
import io.laminext.websocket._
case class MyData()
val myEncode: MyData => MyData = ??? 
WebSocket.path("/my-ws-endpoint").receiveString.sendText(myEncode)
```

## Shortcuts

There are functions to specify the send and receive types at once:

```scala
import io.laminext.websocket._
WebSocket.path("/my-ws-endpoint").string // send and receive strings
WebSocket.path("/my-ws-endpoint").blob // send and receive blobs
WebSocket.path("/my-ws-endpoint").arraybuffer // send and receive array buffers
```

## Configure and build

The final step is to call the `.build` method.

This method accepts the following parameters:

### Managed 

```scala
import io.laminext.websocket._
WebSocket.path("/my-ws-endpoint").string.build(managed = true)
```

When `managed` is `true`, the websocket connection will be managed by the lifecycle of the `.connect` binder:

```scala
import io.laminext.websocket._
val ws = WebSocket.path("/my-ws-endpoint").string.build(managed = true)
div(
  ws.connect  
)
```

When the `div` is mounted, the websocket will try to connect (and re-connect, see below). When the div is unmounted, 
the websocket will disconnect. You can "bind" multiple times (adding `ws.connect` to multiple elements) — in that case
the websocket will connect when the first element is mounted, and disconnect when all of them are unmounted.

If `managed` is `false`, then you control the websocket using its `.disconnectNow()`/`.reconnectNow()` methods, or the 
`disconnect`/`reconnect` observers.

### Buffer sent messages when disconnected

```scala
import io.laminext.websocket._
WebSocket.path("/my-ws-endpoint").string.build(bufferWhenDisconnected = true, bufferSize = 50)
```

When `bufferWhenDisconnected` is `true`, the sent messages will be buffered (up to `bufferSize` messages) and sent out when 
the connection is established. The buffer will be keeping the latest messages (dropping the oldest ones if the `bufferSize` is 
exceeded).

### Auto-reconnect

```scala
import io.laminext.websocket._
import scala.concurrent.duration._
WebSocket.path("/my-ws-endpoint").string
  .build(autoReconnect = true, reconnectDelay = 1.second, reconnectDelayOffline = 20.seconds, reconnectRetries = 10)
```


When in managed mode, and `autoReconnect` is true, the websocket will try to re-connect whenever the connection is closed.

It will try to re-connect up to `reconnectRetries` times, with a delay of `reconnectDelay` between each try.

If the websocket detects that the browser is offline (no internet connection, etc), it will delay for `reconnectDelayOffline` instead.

## Sending messages

You can use the `.sendOne` method: 

```scala
import io.laminext.websocket._

val ws = WebSocket.path("/my-ws-endpoint").string.build()
ws.sendOne("a message")
```

or use the `.send` observer:

```scala
import com.raquo.laminar.api.L._
import io.laminext.websocket._

val ws = WebSocket.path("/my-ws-endpoint").string.build()
val outgoingMessages: EventStream[String] = ???

div(
  ws.connect,
  outgoingMessages --> ws.send
)
```

## Receiving messages 

The websocket provides the `.received` event stream.

## Monitoring the state

Additionally, the websocket provides the following event streams:

```scala
import com.raquo.laminar.api.L._
import io.laminext.websocket._

val ws = WebSocket.path("/my-ws-endpoint").string.build()

ws.connected // EventStream[raw.WebSocket]
             // fires whenever a new connection is estableshed

ws.closed // EventStream[(raw.WebSocket, Boolean)]
          // fires whenever the connection is closed, 
          // the Boolean specifies whether the websocket will try to re-connect 

ws.errors // EventStream[Throwable] a stream of websocket errors

ws.isConnected // Signal[Boolean]

ws.isConnecting // Signal[Boolean]

ws.events // EventStream[WebSocketEvent[Receive]]
          // all websocket events: Connected, Closed, Error, Received

```

## circe support

If you use [circe](https://circe.github.io/circe/), there's a `websocket-circe` module available. See [circe support](/websocket/circe).

