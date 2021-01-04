package io.laminext.site.examples.websocket

import com.raquo.laminar.CollectionCommand
import com.yurique.embedded.FileAsString
import io.laminext.site.examples.CodeExample

object WebSocketExample
    extends CodeExample(
      id = "example-websocket",
      title = "WebSocket",
      description = FileAsString("description.md")
    )(() => {
      import com.raquo.laminar.api.L._
      import io.laminext.syntax.all._
      import io.laminext.websocket

      /* <focus> */
      val ws = websocket.url("wss://echo.websocket.org").string
      /* </focus> */

      val inputElement = input(
        tpe := "text",
        cls := "shadow-sm focus:ring-indigo-500 focus:border-indigo-500 block w-full sm:text-sm border-gray-300 rounded-md",
        placeholder := "send a message"
      )
      div(
        ws.connect,
        cls := "space-y-4",
        div(inputElement),
        div(
          cls := "flex space-x-4",
          button(
            cls := "inline-flex items-center px-3 py-2 border border-blue-200 shadow-sm text-sm leading-4 font-medium rounded-md text-blue-700 bg-white hover:bg-blue-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500",
            "send",
            /* <focus> */ onClick.stream.sample(inputElement.valueSignal) --> ws.send /* </focus> */
          ),
          button(
            cls := "inline-flex items-center px-3 py-2 border border-blue-200 shadow-sm text-sm leading-4 font-medium rounded-md text-blue-700 bg-white focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500",
            "disconnect",
            /* <focus> */ cls <-- ws.isConnected.clsSwitch("hover:bg-blue-50", "opacity-75 cursor-not-allowed"), /* </focus> */
            /* <focus> */ disabled <-- ws.isConnected.map(!_), /* </focus> */
            /* <focus> */ onClick --> ws.disconnect /* </focus> */
          ),
          button(
            cls := "inline-flex items-center px-3 py-2 border border-blue-200 shadow-sm text-sm leading-4 font-medium rounded-md text-blue-700 bg-white hover:bg-blue-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500",
            "connect",
            /* <focus> */ cls <-- ws.isConnected.clsSwitch("opacity-75 cursor-not-allowed", "hover:bg-blue-50"), /* </focus> */
            /* <focus> */ disabled <-- ws.isConnected, /* </focus> */
            /* <focus> */ onClick --> ws.reconnect /* </focus> */
          )
        ),
        div(
          div(
            code("is connected:")
          ),
          div(
            code(
              /* <focus> */ child.text <-- ws.isConnected.map(_.toString) /* </focus> */
            )
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
              cls := "flex flex-col space-y-4 p-4 max-h-48 overflow-auto bg-cool-gray-900 text-green-300",
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
              cls := "flex flex-col space-y-4 p-4 max-h-48 overflow-auto bg-cool-gray-900 text-green-300",
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
