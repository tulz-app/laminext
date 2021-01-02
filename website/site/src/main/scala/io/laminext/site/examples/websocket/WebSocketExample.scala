package io.laminext.site.examples.websocket

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

      val ws = websocket.forUrl("wss://echo.websocket.org").string

      val inputElement = input(
        tpe := "text",
        cls := "shadow-sm focus:ring-indigo-500 focus:border-indigo-500 block w-full sm:text-sm border-gray-300 rounded-md",
        placeholder := "send a message"
      )
      val sendButton = button(
        cls := "inline-flex items-center px-3 py-2 border border-blue-200 shadow-sm text-sm leading-4 font-medium rounded-md text-blue-700 bg-white hover:bg-blue-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500",
        "send",
        onClick.stream.sample(inputElement.valueSignal) --> ws.sendObserver
      )
      div(
        cls := "space-y-4",
        div(inputElement),
        div(sendButton),
        div(
          div(
            code("received:")
          ),
          div(
            cls := "space-x-4",
            ws.connect,
            child.text <-- ws.received.stream
          )
        )
      )
    })
