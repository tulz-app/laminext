package io.laminext.site.examples.ui.ex_transition

import com.yurique.embedded.FileAsString
import io.laminext.site.examples.CodeExample
import io.laminext.ui.theme.TransitionConfig

object TransitionExample
    extends CodeExample(
      id = "example-transition",
      title = "Transition",
      description = FileAsString("description.md")
    )(() => {
      import com.raquo.laminar.api.L._
      import io.laminext.syntax.core._
      import io.laminext.syntax.ui._

      /* <focus> */
      val transitionConfig = TransitionConfig(
        hidden = "hidden",
        inTransition = "transform transition-all motion-reduce:transition-none motion-reduce:transform-none",
        enter = "duration-1000 ease-out",
        enterFrom = "opacity-0 scale-75",
        enterTo = "opacity-100 scale-100",
        leave = "duration-1000 ease-in",
        leaveFrom = "opacity-100 scale-100",
        leaveTo = "opacity-0 scale-75"
      )
      /* </focus> */

      val show            = Var(true)
      val actuallyShowing = Var(true)

      div(
        cls := "p-4 flex flex-col space-y-4",
        div(
          button(
            cls := "btn-md-fill-blue",
            "toggle",
            onClick --> show.toggleObserver
          )
        ),
        div(
          cls := "flex space-x-4 items-center",
          code("show:"),
          code(
            cls := "text-blue-700 font-medium",
            child.text <-- show.signal.map(_.toString)
          )
        ),
        div(
          cls := "flex space-x-4 items-center",
          code("actually showing:"),
          code(
            cls := "text-blue-700 font-medium",
            child.text <-- actuallyShowing.signal.map(_.toString)
          )
        ),
        div(
          cls := "p-8 w-64 bg-blue-600 text-blue-50",
          /* <focus> */
          addTransition(show.signal, transitionConfig, observer = actuallyShowing.writer),
          /* </focus> */
          "I should transition!"
        )
      )
    })
