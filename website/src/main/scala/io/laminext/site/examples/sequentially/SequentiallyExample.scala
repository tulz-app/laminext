package io.laminext.site.examples.sequentially

import com.yurique.embedded.FileAsString
import io.laminext.site.examples.CodeExample

object SequentiallyExample
    extends CodeExample(
      id = "example-sequentially",
      title = "Sequentially example",
      description = FileAsString("description.md")
    )(() => {
      import com.raquo.laminar.api.L._
      import io.laminext.syntax.core._
      val boundVar            = Var(true)
      val (tasks, submitTask) = EventStream.withCallback[String]

      /* <focus> */
      val taskQueue = sequentially(tasks) { task =>
        EventStream.fromValue(task).map(_.toUpperCase).delay(1000)
      }
      /* </focus> */

      val inputElement = input(
        tpe         := "text",
        cls         := "shadow-sm focus:ring-blue-500 focus:border-blue-500 block w-full sm:text-sm border-blue-300 rounded-md bg-blue-50 text-blue-700 placeholder-blue-400 font-mono",
        placeholder := "queue a task"
      )
      div(
        child <-- boundVar.signal.map { bound =>
          if (bound) {
            div(
              /* <focus> */
              taskQueue.bind,
              /* </focus> */
              button(
                cls := "inline-flex items-center px-3 py-2 border border-blue-500 shadow-sm tracking-wide font-medium rounded-md text-blue-100 bg-blue-600 hover:bg-blue-500 hover:text-blue-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500",
                "unbind",
                onClick.mapToFalse --> boundVar,
              )
            )
          } else {
            div(
              button(
                cls := "inline-flex items-center px-3 py-2 border border-blue-500 shadow-sm tracking-wide font-medium rounded-md text-blue-100 bg-blue-600 hover:bg-blue-500 hover:text-blue-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500",
                "bind",
                onClick.mapToTrue --> boundVar,
              )
            )
          }
        },
        cls := "space-y-4",
        div(inputElement),
        div(
          cls := "flex space-x-4",
          button(
            cls := "inline-flex items-center px-3 py-2 border border-blue-500 shadow-sm tracking-wide font-medium rounded-md text-blue-100 bg-blue-600 hover:bg-blue-500 hover:text-blue-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500",
            "send",
            thisEvents(onClick)
              .sample(inputElement.value) --> submitTask,
          ),
        ),
        div(
          div(
            code("output stream:")
          ),
          div(
            cls := "flex flex-col space-y-4 p-4 max-h-96 overflow-auto bg-gray-900",
            /* <focus> */
            children.command <-- taskQueue.output.map { output =>
              /* </focus> */
              CollectionCommand.Append(
                div(
                  div(
                    cls := "flex space-x-2 items-center",
                    code(cls := "text-green-500", "Status: "),
                    code(cls := "text-green-300", output)
                  ),
                )
              )
            }
          )
        )
      )
    })
