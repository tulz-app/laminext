package io.laminext.site.examples.fetch

import com.yurique.embedded.FileAsString
import io.laminext.site.examples.CodeExample

object FetchPostExample
    extends CodeExample(
      id = "example-fetch-post",
      title = "POST example",
      description = FileAsString("description.md")
    )(() => {
      import com.raquo.laminar.api.L._
      import io.laminext.syntax.core._
      import io.laminext.fetch.Fetch
      import io.laminext.fetch.FetchResponse
      import scala.util.Failure
      import scala.util.Success
      import org.scalajs.macrotaskexecutor.MacrotaskExecutor.Implicits._

      val inputElement                        = input(
        tpe         := "text",
        placeholder := "send a message"
      )
      val (responsesStream, responseReceived) = EventStream.withCallback[FetchResponse[String]]
      div(
        cls := "space-y-4",
        div(inputElement),
        div(
          cls := "flex space-x-4",
          button(
            cls := "btn-md-fill-blue",
            "send",
            thisEvents(onClick)
              .sample(inputElement.value)
              .flatMapSwitch { inputValue =>
                /* <focus> */
                Fetch
                  .post("https://httpbin.org/anything", body = inputValue)
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
                      code(response.data)
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
