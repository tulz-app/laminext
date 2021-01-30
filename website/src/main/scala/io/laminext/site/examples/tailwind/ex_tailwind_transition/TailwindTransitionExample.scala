package io.laminext.site.examples.tailwind.ex_tailwind_transition

import com.yurique.embedded.FileAsString
import io.laminext.site.examples.CodeExample

object TailwindTransitionExample
    extends CodeExample(
      id = "example-transition",
      title = "Transitions",
      description = FileAsString("description.md")
    )(() => {
      import com.raquo.laminar.api.L._
      import io.laminext.syntax.core._
      import io.laminext.syntax.tailwind._

      val show1            = Var(true)
      val actuallyShowing1 = Var(true)
      val show2            = Var(true)
      val actuallyShowing2 = Var(true)

      div(
        cls := "space-y-4",
        div(
          cls := "p-4 flex flex-col space-y-4",
          div(
            button(
              cls := "inline-flex items-center px-3 py-2 border border-blue-500 shadow-sm tracking-wide font-medium rounded-md text-blue-100 bg-blue-600 hover:bg-blue-500 hover:text-blue-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500",
              "toggle",
              onClick --> show1.toggleObserver
            )
          ),
          div(
            cls := "flex space-x-4 items-center",
            code("show:"),
            code(
              cls := "text-blue-700 font-medium",
              child.text <-- show1.signal.map(_.toString)
            )
          ),
          div(
            cls := "flex space-x-4 items-center",
            code("actually showing:"),
            code(
              cls := "text-blue-700 font-medium",
              child.text <-- actuallyShowing1.signal.map(_.toString)
            )
          ),
          div(
            cls := "p-8 w-64 bg-blue-600 text-blue-50",
            /* <focus> */
            TW.transition.opacityAndScale(show1.signal, observer = actuallyShowing1.writer),
            /* </focus> */
            "I should transition (opacity and scale)!"
          )
        ),
        div(
          cls := "p-4 flex flex-col space-y-4",
          div(
            button(
              cls := "inline-flex items-center px-3 py-2 border border-blue-500 shadow-sm tracking-wide font-medium rounded-md text-blue-100 bg-blue-600 hover:bg-blue-500 hover:text-blue-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500",
              "toggle",
              onClick --> show2.toggleObserver
            )
          ),
          div(
            cls := "flex space-x-4 items-center",
            code("show:"),
            code(
              cls := "text-blue-700 font-medium",
              child.text <-- show2.signal.map(_.toString)
            )
          ),
          div(
            cls := "flex space-x-4 items-center",
            code("actually showing:"),
            code(
              cls := "text-blue-700 font-medium",
              child.text <-- actuallyShowing2.signal.map(_.toString)
            )
          ),
          div(
            cls := "p-8 w-64 bg-blue-600 text-blue-50",
            /* <focus> */
            TW.transition.opacity(show2.signal, observer = actuallyShowing2.writer),
            /* </focus> */
            "I should transition (opacity)!"
          )
        )
      )
    })
