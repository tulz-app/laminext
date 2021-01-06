package io.laminext.site.examples.signal.ex_var_of_boolean_toggle

import io.laminext.site.examples.CodeExample
import com.yurique.embedded.FileAsString

object VarOfBooleanToggleExample
    extends CodeExample(
      id = "example-var-of-boolean-toggle",
      title = "Var of Boolean Toggle",
      description = FileAsString("description.md")
    )(() => {
      import io.laminext.syntax.all._
      import com.raquo.laminar.api.L._

      val aVar = Var(false)

      val toggleButton = button(
        cls := "inline-flex items-center px-3 py-2 border border-blue-500 shadow-sm tracking-wide font-medium rounded-md text-blue-100 bg-blue-600 hover:bg-blue-500 hover:text-blue-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500",
        "toggle",
        onClick --> { _ => aVar.toggle() }
      )
      val toggleButton2 = button(
        cls := "inline-flex items-center px-3 py-2 border border-blue-500 shadow-sm tracking-wide font-medium rounded-md text-blue-100 bg-blue-600 hover:bg-blue-500 hover:text-blue-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500",
        "toggle (via .toggleObserver)",
        onClick --> aVar.toggleObserver
      )

      div(
        cls := "space-y-4",
        div(
          toggleButton
        ),
        div(
          toggleButton2
        ),
        div(
          cls := "flex space-x-4 items-center",
          code("signal:"),
          code(
            cls := "text-blue-700 font-medium",
            child.text <-- aVar.signal.map(_.toString)
          )
        )
      )
    })
