package io.laminext.websocket

package object upickle extends ReExports {

  implicit def webSocketReceiveBuilderSyntax(b: WebSocketReceiveBuilder): WebSocketReceiveBuilderUpickleOps =
    new WebSocketReceiveBuilderUpickleOps(b)

}
