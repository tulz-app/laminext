package io.laminext.site.examples.ui.ex_transition

import com.yurique.embedded.FileAsString
import io.laminext.site.examples.CodeExample

object TransitionExample
    extends CodeExample(
      id = "example-transition",
      title = "Transition",
      description = FileAsString("description.md")
    )(() => {
      import com.raquo.laminar.api.L._
      import io.laminext.syntax.all._
      import io.laminext.ui.transition.Transition
      import io.laminext.ui.transition.TransitionConfig

      val transitionConfig = TransitionConfig(
        inTransition = "transform transition-all motion-reduce:transition-none motion-reduce:transform-none",
        enterDuration = "duration-1000",
        enterTiming = "ease-out",
        enterFrom = "opacity-0 scale-75",
        enterTo = "opacity-100 scale-100",
        leaveDuration = "duration-1000",
        leaveTiming = "ease-in",
        leaveFrom = "opacity-100 scale-100",
        leaveTo = "opacity-0 scale-75"
      )

      val show            = Var(true)
      val actuallyShowing = Var(true)

      div(
        cls := "p-4 flex flex-col space-y-4",
        div(
          button(
            cls := "inline-flex items-center px-3 py-2 border border-blue-200 shadow-sm text-sm leading-4 font-medium rounded-md text-blue-700 bg-white hover:bg-blue-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500",
            "toggle",
            onClick --> show.toggleObserver
          )
        ),
        div(
          cls := "flex space-x-4 items-center",
          code("show:"),
          code(
            cls := "text-blue-600",
            child.text <-- show.signal.map(_.toString)
          )
        ),
        div(
          cls := "flex space-x-4 items-center",
          code("actually showing:"),
          code(
            cls := "text-blue-600",
            child.text <-- actuallyShowing.signal.map(_.toString)
          )
        ),
        div(
          cls := "p-8 w-64  bg-blue-600 text-blue-50",
          Transition(show.signal, transitionConfig, observer = actuallyShowing.writer),
          "I should transition!"
        )
      )
    })
