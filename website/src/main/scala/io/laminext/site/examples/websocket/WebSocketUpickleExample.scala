package io.laminext.site.examples.websocket

import com.yurique.embedded.FileAsString
import io.laminext.site.examples.CodeExample

case class Data(s: String)

object Data {
  import upickle.default._
  implicit val dataReader: Reader[Data] = reader[Map[String, String]].map(m => Data(m("s")))
  implicit val dataWriter: Writer[Data] = writer[Map[String, String]].comap(d => Map("s" -> d.s))
}

object WebSocketUpickleExample
    extends CodeExample(
      id = "example-websocket-echo-upickle",
      title = "upickle example",
      description = FileAsString("description.md")
    )(() => {
      import com.raquo.laminar.api.L._
      import com.raquo.laminar.CollectionCommand
      import io.laminext.syntax.core._
      import io.laminext.websocket.upickle._
      import upickle.default._

      /*
      case class Data(s: String)
      object Data {
        implicit val rw: ReadWriter[Data] = macroRW[Data]
      }
       */

      /* <focus> */
      val ws = WebSocket.url("wss://echo.websocket.org").json[Data, Data].build()
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
