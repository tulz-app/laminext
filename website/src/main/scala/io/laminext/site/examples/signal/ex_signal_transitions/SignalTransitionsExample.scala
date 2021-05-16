package io.laminext.site.examples.signal.ex_signal_transitions

import io.laminext.site.examples.CodeExample
import com.yurique.embedded.FileAsString

object SignalTransitionsExample
    extends CodeExample(
      id = "example-signal-transitions",
      title = "Signal Transitions",
      description = FileAsString("description.md")
    )(() => {
      import com.raquo.laminar.api.L._
      import io.laminext.syntax.core._

      val aVar = Var("initial")

      val inputElement = input(
        tpe := "text",
        placeholder := "new value"
      )
      div(
        cls := "space-y-4",
        div(inputElement),
        div(
          button(
            cls := "btn-md-fill-blue",
            "update signal",
            thisEvents(onClick).sample(inputElement.value) --> aVar.writer
          )
        ),
        div(
          cls := "flex space-x-4 items-center",
          code("signal:"),
          code(
            cls := "text-blue-700 font-medium",
            child.text <-- aVar.signal
          )
        ),
        div(
          cls := "flex space-x-4 items-center",
          code("signal.transitions:"),
          code(
            cls := "text-blue-700 font-medium",
            /* <focus> */
            child.text <-- aVar.signal.transitions.map(_.toString())
            /* </focus> */
          )
        )
      )
    })
