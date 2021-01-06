package io.laminext.site.examples.signal.ex_signal_of_boolean_class_switch

import io.laminext.site.examples.CodeExample
import com.yurique.embedded.FileAsString

object SignalOfBooleanClassSwitchExample
    extends CodeExample(
      id = "example-signal-of-boolean-class-switch",
      title = "Signal of Boolean Class Switch",
      description = FileAsString("description.md")
    )(() => {
      import io.laminext.syntax.all._
      import com.raquo.laminar.api.L._

      val booleanVar = Var(false)

      val toggleButton = button(
        cls := "inline-flex items-center px-3 py-2 border border-blue-500 shadow-sm tracking-wide font-medium rounded-md text-blue-100 bg-blue-600 hover:bg-blue-500 hover:text-blue-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500",
        "toggle",
        onClick.mapToUnit --> { _ => booleanVar.toggle() }
      )

      div(
        cls := "space-y-4",
        div(
          toggleButton
        ),
        div(
          cls := "flex space-x-4 items-center  ",
          code("signal:"),
          code(
            cls := "text-blue-600",
            child.text <-- booleanVar.signal.map(_.toString)
          )
        ),
        div(
          cls := "p-8",
          booleanVar.signal.classSwitch(
            whenTrue = "bg-green-600 text-green-50",
            whenFalse = "bg-yellow-600 text-yellow-50",
          ),
          "class switch"
        )
      )
    })
