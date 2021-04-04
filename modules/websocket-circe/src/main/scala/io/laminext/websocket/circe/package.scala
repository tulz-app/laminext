package io.laminext.websocket

package object circe extends ReExports {

  implicit def webSocketReceiveBuilderSyntax(b: WebSocketReceiveBuilder): WebSocketReceiveBuilderCirceOps =
    new WebSocketReceiveBuilderCirceOps(b)

}
