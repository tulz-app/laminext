package io.laminext.site.examples.websocket

import com.yurique.embedded.FileAsString
import io.laminext.site.examples.CodeExample

object WebSocketUpickleExample
    extends CodeExample(
      id = "example-websocket-echo-upickle",
      title = "upickle example",
      description = FileAsString("description.md")
    )(() => {
      import com.raquo.laminar.api.L._
      import com.raquo.laminar.api.L.CollectionCommand
      import io.laminext.syntax.core._
      import io.laminext.websocket.upickle._

      object OptionPickler extends upickle.AttributeTagged {
        implicit override def OptionWriter[T: Writer]: Writer[Option[T]] =
          implicitly[Writer[T]].comap[Option[T]] {
            case None    => null.asInstanceOf[T]
            case Some(x) => x
          }

        implicit override def OptionReader[T: Reader]: Reader[Option[T]] = {
          new Reader.Delegate[Any, Option[T]](implicitly[Reader[T]].map(Some(_))) {
            override def visitNull(index: Int): Option[T] = None
          }
        }
      }

      import OptionPickler._

      case class Data(s: String)

      implicit val dataReader: Reader[Data] = reader[Map[String, String]].map(m => Data(m("s")))
      implicit val dataWriter: Writer[Data] = writer[Map[String, String]].comap(d => Map("s" -> d.s))

      /* <focus> */
      val ws = WebSocket.url("wss://echo.websocket.events").upickle(OptionPickler).json[Data, Data].build()
      /* </focus> */
      // or, when using upickle.default:
      // val ws = WebSocket.url("wss://echo.websocket.events").json[Data, Data].build()

      val inputElement = input(
        tpe         := "text",
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
