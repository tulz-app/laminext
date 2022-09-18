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
            cls := "btn-md-fill-blue",
            "click me",
            /* <focus> */
            thisEvents(onClick.preventDefault)
              .sample(inputElement.value)
              .map(_.toUpperCase)
              .collect {
                case s if s.trim.nonEmpty => s
              }
              .foreach { s =>
                dom.window.alert(s"You typed: ${s}")
              }
            /* </focus> */
          )
        )
      )
    })
