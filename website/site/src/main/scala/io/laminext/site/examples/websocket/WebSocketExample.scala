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

      val ws = websocket.url("wss://echo.websocket.org").string

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
            onClick.stream.sample(inputElement.valueSignal) --> ws.send
          ),
          button(
            cls := "inline-flex items-center px-3 py-2 border border-blue-200 shadow-sm text-sm leading-4 font-medium rounded-md text-blue-700 bg-white focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500",
            "disconnect",
            cls <-- ws.isConnected.clsSwitch("hover:bg-blue-50", "opacity-75 cursor-not-allowed"),
            disabled <-- ws.isConnected.map(!_),
            onClick --> ws.disconnect
          ),
          button(
            cls := "inline-flex items-center px-3 py-2 border border-blue-200 shadow-sm text-sm leading-4 font-medium rounded-md text-blue-700 bg-white hover:bg-blue-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500",
            cls <-- ws.isConnected.clsSwitch("opacity-75 cursor-not-allowed", "hover:bg-blue-50"),
            "connect",
            disabled <-- ws.isConnected,
            onClick --> ws.reconnect
          )
        ),
        div(
          div(
            code("is connected:")
          ),
          div(
            code(
              child.text <-- ws.isConnected.map(_.toString)
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
              children.command <-- ws.received.map { message =>
                CollectionCommand.Append(
                  code(message)
                )
              }
            )
          ),
          div(
            cls := "flex-1",
            div(
              code("events:")
            ),
            div(
              cls := "flex flex-col space-y-4 p-4 max-h-48 overflow-auto bg-cool-gray-900 text-green-300",
              children.command <-- ws.events.map { event =>
                CollectionCommand.Append(
                  code(event.toString)
                )
              }
            )
          )
        ),
      )
    })
