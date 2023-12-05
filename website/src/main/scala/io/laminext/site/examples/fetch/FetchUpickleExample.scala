package io.laminext.site.examples.fetch

import com.yurique.embedded.FileAsString
import io.laminext.site.examples.CodeExample

object FetchUpickleExample
    extends CodeExample(
      id = "example-fetch-upickle",
      title = "upickle example",
      description = FileAsString("description.md")
    )(() => {
      import com.raquo.laminar.api.L._
      import io.laminext.syntax.core._
      import io.laminext.fetch.upickle._
      import upickle.default._
      import scala.util.Failure
      import scala.util.Success
      import com.raquo.laminar.api.L.CollectionCommand
      import org.scalajs.macrotaskexecutor.MacrotaskExecutor.Implicits._

      case class Data(s: String)
      implicit val codeData: Writer[Data] = writer[Map[String, String]].comap(d => Map("s" -> d.s))

      val inputElement = input(
        tpe         := "text",
        cls         := "shadow-sm focus:ring-blue-500 focus:border-blue-500 block w-full sm:text-sm border-blue-300 rounded-md bg-blue-50 text-blue-700 placeholder-blue-400 font-mono",
        placeholder := "send a message"
      )

      val (responsesStream, responseReceived) = EventStream.withCallback[FetchResponse[String]]
      div(
        EventStream.fromValue(1).debugLogStarts --> { _ => },
        cls := "space-y-4",
        div(inputElement),
        div(
          cls := "flex space-x-4",
          button(
            cls := "inline-flex items-center px-3 py-2 border border-blue-500 shadow-sm tracking-wide font-medium rounded-md text-blue-100 bg-blue-600 hover:bg-blue-500 hover:text-blue-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500",
            "send",
            thisEvents(onClick)
              .sample(inputElement.value)
              .flatMapSwitch { inputValue =>
                /* <focus> */
                Fetch
                  .post("https://httpbin.org/anything", body = jsonRequestBody(Data(s = inputValue))) // Data has an implicit Encoder[Data] auto-derived
                  .text
                /* </focus> */
              } --> responseReceived,
          ),
        ),
        div(
          div(
            code("received:")
          ),
          div(
            cls := "flex flex-col space-y-4 p-4 max-h-96 overflow-auto bg-gray-900",
            children.command <-- responsesStream.recoverToTry.map {
              case Success(response)  =>
                CollectionCommand.Append(
                  div(
                    div(
                      cls := "flex space-x-2 items-center",
                      code(cls := "text-green-500", "Status: "),
                      code(cls := "text-green-300", s"${response.status} ${response.statusText}")
                    ),
                    div(
                      cls := "text-green-400 text-xs",
                      pre(response.data)
                    )
                  )
                )
              case Failure(exception) =>
                CollectionCommand.Append(
                  div(
                    div(
                      cls := "flex space-x-2 items-center",
                      code(cls := "text-red-500", "Error: "),
                      code(cls := "text-red-300", exception.getMessage)
                    )
                  )
                )
            }
          )
        )
      )
    })
