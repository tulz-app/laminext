package io.laminext.site.examples.websocket

import com.raquo.laminar.CollectionCommand
import com.yurique.embedded.FileAsString
import io.laminext.site.examples.CodeExample

object WebSocketExample
    extends CodeExample(
      id = "example-websocket-echo",
      title = "Echo example",
      description = FileAsString("description.md")
    )(() => {
      import com.raquo.laminar.api.L._
      import io.laminext.syntax.core._
      import io.laminext.websocket._

      /* <focus> */
      val ws = WebSocket.url("wss://echo.websocket.org").string.build(managed = false)
      /* </focus> */

      val inputElement = input(
        tpe := "text",
        placeholder := "send a message"
      )
      div(
        ws.connect,
        cls := "space-y-2",
        div(inputElement),
        div(
          cls := "flex space-x-4",
          button(
            cls := "btn-md-fill-blue",
            "send",
            /* <focus> */
            thisEvents(onClick).sample(inputElement.value) --> ws.send
            /* </focus> */
          ),
          button(
            cls := "btn-md-fill-blue disabled:opacity-75 disabled:text-opacity-75 disabled:cursor-not-allowed",
            "disconnect",
            /* <focus> */
            disabled <-- !(ws.isConnected || ws.isConnecting),
            onClick --> ws.disconnect
            /* </focus> */
          ),
          button(
            cls := "btn-md-fill-blue disabled:opacity-75 disabled:text-opacity-75 disabled:cursor-not-allowed",
            "connect",
            /* <focus> */
            disabled <-- (ws.isConnected || ws.isConnecting),
            onClick --> ws.reconnect
            /* </focus> */
          )
        ),
        div(
          cls := "flex space-x-2",
          code("connecting:"),
          code(
            /* <focus> */
            child.text <-- ws.isConnecting.map(_.toString)
            /* </focus> */
          )
        ),
        div(
          cls := "flex space-x-2",
          code("connected:"),
          code(
            /* <focus> */
            child.text <-- ws.isConnected.map(_.toString)
            /* </focus> */
          )
        ),
        div(
          cls := "flex space-x-8",
          div(
            cls := "flex-1",
            div(
              code("received:")
            ),
            div(
              cls := "flex flex-col space-y-4 p-4 max-h-48 overflow-auto bg-gray-900 text-green-400 text-xs",
              /* <focus> */
              children.command <-- ws.received.map { message =>
                CollectionCommand.Append(
                  code(message)
                )
              }
              /* </focus> */
            )
          ),
          div(
            cls := "flex-1",
            div(
              code("events:")
            ),
            div(
              cls := "flex flex-col space-y-4 p-4 max-h-48 overflow-auto bg-gray-900 text-green-400 text-xs",
              /* <focus> */
              children.command <-- ws.events.map { event =>
                CollectionCommand.Append(
                  code(event.toString)
                )
              }
              /* </focus> */
            )
          )
        ),
      )
    })
