package io.laminext.site.examples.signal.ex_var_of_boolean_toggle

import io.laminext.site.examples.CodeExample
import com.yurique.embedded.FileAsString

object VarOfBooleanToggleExample
    extends CodeExample(
      id = "example-var-of-boolean-toggle",
      title = "Var of Boolean Toggle",
      description = FileAsString("description.md")
    )(() => {
      import io.laminext.syntax.core._
      import com.raquo.laminar.api.L._

      val booleanVar = Var(false)

      div(
        cls := "space-y-4",
        div(
          button(
            cls := "btn-md-fill-blue",
            "toggle",
            /* <focus> */
            onClick --> { _ => booleanVar.toggle() }
            /* </focus> */
          )
        ),
        div(
          button(
            cls := "btn-md-fill-blue",
            "toggle (via .toggleObserver)",
            /* <focus> */
            onClick --> booleanVar.toggleObserver
            /* </focus> */
          )
        ),
        div(
          cls := "flex space-x-4 items-center",
          code("signal:"),
          code(
            cls := "text-blue-700 font-medium",
            child.text <-- booleanVar.signal.map(_.toString)
          )
        )
      )
    })
