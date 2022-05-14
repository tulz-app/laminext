package io.laminext.site.examples.websocket

import com.yurique.embedded.FileAsString
import io.laminext.site.examples.CodeExample

object WebSocketCirceExample
    extends CodeExample(
      id = "example-websocket-echo-circe",
      title = "circe example",
      description = FileAsString("description.md")
    )(() => {
      import com.raquo.laminar.api.L._
      div(
        "temporarily commenting this out – circe's not ready for scala 3-RC2"
      )

      import com.raquo.laminar.api.L._
      import com.raquo.laminar.CollectionCommand
      import io.laminext.syntax.core._
      import io.laminext.websocket.circe._
      import io.circe._

      case class Data(s: String)
      implicit val codeData: Codec[Data] = Codec.from(
        Decoder.decodeString.map(Data(_)),
        Encoder.encodeString.contramap(_.s),
      )

      /* <focus> */
      val ws = WebSocket.url("wss://echo.websocket.events").json[Data, Data].build()
      /* </focus> */

      val inputElement = input(
        tpe         := "text",
        cls         := "shadow-sm focus:ring-blue-500 focus:border-blue-500 block w-full sm:text-sm border-blue-300 rounded-md bg-blue-50 text-blue-700 placeholder-blue-400 font-mono",
        placeholder := "send a message"
      )
      div(
        ws.connect,
        cls := "space-y-2",
        div(inputElement),
        div(
          cls := "flex space-x-4",
          button(
            cls := "inline-flex items-center px-3 py-2 border border-blue-500 shadow-sm tracking-wide font-medium rounded-md text-blue-100 bg-blue-600 hover:bg-blue-500 hover:text-blue-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500",
            "send",
            /* <focus> */
            thisEvents(onClick).sample(inputElement.value).map(string => Data(string)) --> ws.send
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
                  code(message.toString)
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
