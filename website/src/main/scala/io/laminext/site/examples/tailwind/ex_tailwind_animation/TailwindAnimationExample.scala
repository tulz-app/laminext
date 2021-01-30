package io.laminext.site.examples.tailwind.ex_tailwind_animation

import com.yurique.embedded.FileAsString
import io.laminext.site.examples.CodeExample

object TailwindAnimationExample
    extends CodeExample(
      id = "example-animation",
      title = "Animation",
      description = FileAsString("description.md")
    )(() => {
      import com.raquo.laminar.api.L._
      import io.laminext.syntax.tailwind._

      val bounce = Var(Option.empty[String])

      div(
        cls := "p-4 flex flex-col space-y-4",
        div(
          button(
            cls := "inline-flex items-center px-3 py-2 border border-blue-500 shadow-sm tracking-wide font-medium rounded-md text-blue-100 bg-blue-600 hover:bg-blue-500 hover:text-blue-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500",
            "toggle",
            onClick --> { _ =>
              bounce.update(s =>
                if (s.isEmpty) {
                  Some("animate-wiggle")
                } else {
                  None
                }
              )
            }
          )
        ),
        div(
          cls := "flex space-x-4 items-center",
          code("bounceSignal:"),
          code(
            cls := "text-blue-700 font-medium",
            child.text <-- bounce.signal.map(_.toString)
          )
        ),
        div(
          cls := "p-8 w-64 bg-blue-600 text-blue-50",
          /* <focus> */
          TW.animation(bounce.signal),
          /* </focus> */
          "I should bounce!"
        )
      )
    })
