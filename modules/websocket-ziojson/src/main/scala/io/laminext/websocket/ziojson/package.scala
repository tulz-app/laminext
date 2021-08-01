package io.laminext.websocket

package object ziojson extends ReExports {
  implicit def webSocketReceiveBuilderSyntax(b: WebSocketReceiveBuilder): WebSocketReceiveBuilderZioJsonOps =
    new WebSocketReceiveBuilderZioJsonOps(b)

}
