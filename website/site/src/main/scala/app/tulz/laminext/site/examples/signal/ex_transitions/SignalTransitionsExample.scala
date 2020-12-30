package app.tulz.laminext.site.examples.signal.ex_transitions

import app.tulz.laminext.site.examples.CodeExample
import app.tulz.website.macros.FileToLiteral

object SignalTransitionsExample
    extends CodeExample(
      id = "signal-transitions",
      title = "Signal Transitions",
      description = FileToLiteral("description.md")
    )(() => {
      import com.raquo.laminar.api.L._
      import app.tulz.laminar.ext._

      val aVar = Var("initial")

      val inputElement = input(
        tpe := "text",
        cls := "shadow-sm focus:ring-indigo-500 focus:border-indigo-500 block w-full sm:text-sm border-gray-300 rounded-md",
        placeholder := "new value"
      )
      val updateButton = button(
        cls := "inline-flex items-center px-3 py-2 border border-blue-200 shadow-sm text-sm leading-4 font-medium rounded-md text-blue-700 bg-white hover:bg-blue-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500",
        "update signal"
      )

      div(
        updateButton.events(onClick).mapTo(inputElement.ref.value) --> aVar.writer,
        cls := "space-y-4",
        div(inputElement),
        div(updateButton),
        div(
          cls := "flex space-x-4 items-center",
          code("signal:"),
          code(
            cls := "text-blue-600",
            child.text <-- aVar.signal
          )
        ),
        div(
          cls := "flex space-x-4 items-center",
          code("signal.transitions:"),
          code(
            cls := "text-blue-600",
            child.text <-- aVar.signal.transitions.map(_.toString())
          )
        )
      )
    })
