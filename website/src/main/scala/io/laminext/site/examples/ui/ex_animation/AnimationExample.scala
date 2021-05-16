package io.laminext.site.examples.ui.ex_animation

import com.yurique.embedded.FileAsString
import io.laminext.site.examples.CodeExample
import io.laminext.ui

object AnimationExample
    extends CodeExample(
      id = "example-animation",
      title = "Animation",
      description = FileAsString("description.md")
    )(() => {
      import com.raquo.laminar.api.L._
      import io.laminext.ui._

      val bounce = Var(Option.empty[String])

      div(
        cls := "p-4 flex flex-col space-y-4",
        div(
          button(
            cls := "btn-md-fill-blue",
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
          addAnimation(bounce.signal),
          /* </focus> */
          "I should bounce!"
        )
      )
    })
