package io.laminext.site.examples.fetch

import com.raquo.laminar.CollectionCommand
import com.yurique.embedded.FileAsString
import io.laminext.fetch.FetchResponse
import io.laminext.site.examples.CodeExample

import scala.concurrent.duration.DurationInt
import scala.util.Failure
import scala.util.Success

object FetchExample
    extends CodeExample(
      id = "example-fetch",
      title = "fetch",
      description = FileAsString("description.md")
    )(() => {
      import com.raquo.laminar.api.L._
      import io.laminext.syntax.all._
      import io.laminext.fetch.Fetch
      import org.scalajs.dom

      val inputElement = input(
        tpe := "text",
        cls := "shadow-sm focus:ring-indigo-500 focus:border-indigo-500 block w-full sm:text-sm border-gray-300 rounded-md",
        placeholder := "send a message"
      )
      val (responsesStream, responseReceived) = EventStream.withCallback[FetchResponse[String]]
      div(
        cls := "space-y-4",
        div(inputElement),
        div(
          cls := "flex space-x-4",
          button(
            cls := "inline-flex items-center px-3 py-2 border border-blue-500 shadow-sm tracking-wide font-medium rounded-md text-blue-100 bg-blue-600 hover:bg-blue-500 hover:text-blue-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500",
            "send",
            /* <focus> */
            thisEvents(onClick)
              .sample(inputElement.value)
              .flatMap(text => Fetch.post("https://httpbin.org/anything", body = text).text) --> responseReceived,
            /* </focus> */
          ),
        ),
        div(
          div(
            code("received:")
          ),
          div(
            cls := "flex flex-col space-y-4 p-4 max-h-96 overflow-auto bg-cool-gray-900",
            /* <focus> */
            children.command <-- responsesStream.recoverToTry.map {
              case Success(response) =>
                CollectionCommand.Append(
                  div(
                    div(
                      cls := "flex space-x-2 items-center",
                      code(cls := "text-green-500", "Status: "),
                      code(cls := "text-green-300", s"${response.status} - ${response.statusText}")
                    ),
                    div(
                      cls := "text-green-400 text-xs",
                      code(response.data)
                    )
                  )
                )
              case Failure(exception) =>
                dom.console.error(exception.getMessage)
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
            /* </focus> */
          )
        )
      )
    })
