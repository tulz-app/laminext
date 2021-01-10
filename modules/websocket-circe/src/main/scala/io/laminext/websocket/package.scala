package io.laminext

package object websocket {

  implicit def webSocketReceiveBuilderSyntax(b: WebSocketReceiveBuilder): WebSocketReceiveBuilderOps =
    new WebSocketReceiveBuilderOps(b)

}
