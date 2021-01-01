package io.laminext.site.examples.ui.ex_animation

import app.tulz.website.macros.FileToLiteral
import io.laminext.site.examples.CodeExample

object AnimationExample
    extends CodeExample(
      id = "example-animation",
      title = "Animation",
      description = FileToLiteral("description.md")
    )(() => {
      import com.raquo.laminar.api.L._
      import io.laminext.ui.animation.Animation

      val bounceSignal = Var(Option.empty[String])

      div(
        cls := "p-4 flex flex-col space-y-4",
        div(
          button(
            cls := "inline-flex items-center px-3 py-2 border border-blue-200 shadow-sm text-sm leading-4 font-medium rounded-md text-blue-700 bg-white hover:bg-blue-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500",
            "toggle",
            onClick --> { _ =>
              bounceSignal.update(s =>
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
            cls := "text-blue-600",
            child.text <-- bounceSignal.signal.map(_.toString)
          )
        ),
        div(
          cls := "p-8 w-64  bg-blue-600 text-blue-50",
          Animation(bounceSignal.signal),
          "I should bounce!"
        )
      )
    })
