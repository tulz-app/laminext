package app.tulz.laminext.site.examples.signal.ex_boolean_toggle

import app.tulz.laminext.site.examples.CodeExample
import app.tulz.website.macros.FileToLiteral

object VarOfBooleanToggleExample
    extends CodeExample(
      id = "var-of-boolean-toggle",
      title = "Var[Boolean] Toggle",
      description = FileToLiteral("description.md")
    )(() => {
      import app.tulz.laminar.ext._
      import com.raquo.laminar.api.L._

      val aVar = Var(false)

      val toggleButton = button(
        cls := "inline-flex items-center px-3 py-2 border border-gray-300 shadow-sm text-sm leading-4 font-medium rounded-md text-gray-700 bg-white hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500",
        "toggle",
        onClick --> { _ => aVar.toggle() }
      )

      div(
        cls := "space-y-1",
        div(
          toggleButton
        ),
        div(
          cls := "flex space-x-1 items-center",
          span("Signal: "),
          code(
            cls := "text-blue-600",
            child.text <-- aVar.signal.map(_.toString)
          )
        )
      )
    })
