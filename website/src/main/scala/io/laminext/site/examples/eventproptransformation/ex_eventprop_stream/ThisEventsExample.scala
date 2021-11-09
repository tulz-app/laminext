package io.laminext.site.examples.eventproptransformation.ex_eventprop_stream

import io.laminext.site.examples.CodeExample
import com.yurique.embedded.FileAsString

object ThisEventsExample
    extends CodeExample(
      id = "example-this-events",
      title = "thisEvents",
      description = FileAsString("description.md")
    )(() => {
      import com.raquo.laminar.api.L._
      import io.laminext.syntax.core._
      import org.scalajs.dom

      val inputElement = input(
        tpe         := "text",
        cls         := "shadow-sm focus:ring-blue-500 focus:border-blue-500 block w-full sm:text-sm border-blue-300 rounded-md bg-blue-50 text-blue-700 placeholder-blue-400 font-mono",
        placeholder := "set value"
      )

      div(
        cls := "space-y-4",
        "hi",
        div(
          inputElement
        ),
        div(
          button(
            cls := "inline-flex items-center px-3 py-2 border border-blue-500 shadow-sm tracking-wide font-medium rounded-md text-blue-100 bg-blue-600 hover:bg-blue-500 hover:text-blue-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500",
            "click me",
            /* <focus> */
            thisEvents(onClick.preventDefault)
              .sample(inputElement.value)
              .map(_.toUpperCase)
              .collect {
                case s if s.trim.nonEmpty => s
              }.foreach { s =>
                dom.window.alert(s"You typed: ${s}")
              }
            /* </focus> */
          )
        )
      )
    })
