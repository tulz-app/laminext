package io.laminext.site.examples.signal.ex_var_of_boolean_toggle

import io.laminext.site.examples.CodeExample
import app.tulz.website.macros.FileToLiteral

object VarOfBooleanToggleExample
    extends CodeExample(
      id = "example-var-of-boolean-toggle",
      title = "Var of Boolean Toggle",
      description = FileToLiteral("description.md")
    )(() => {
      import io.laminext.syntax.all._
      import com.raquo.laminar.api.L._

      val aVar = Var(false)

      val toggleButton = button(
        cls := "inline-flex items-center px-3 py-2 border border-blue-200 shadow-sm text-sm leading-4 font-medium rounded-md text-blue-700 bg-white hover:bg-blue-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500",
        "toggle",
        onClick --> { _ => aVar.toggle() }
      )

      div(
        cls := "space-y-4",
        div(
          toggleButton
        ),
        div(
          cls := "flex space-x-4 items-center",
          code("signal:"),
          code(
            cls := "text-blue-600",
            child.text <-- aVar.signal.map(_.toString)
          )
        )
      )
    })
