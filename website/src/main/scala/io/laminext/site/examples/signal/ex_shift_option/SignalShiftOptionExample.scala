package io.laminext.site.examples.signal.ex_shift_option

import io.laminext.site.examples.CodeExample
import com.yurique.embedded.FileAsString

object SignalShiftOptionExample
    extends CodeExample(
      id = "example-signal-shift-option",
      title = "Signal shiftOption",
      description = FileAsString("description.md")
    )(() => {
      import io.laminext.syntax.core._
      import com.raquo.laminar.api.L._

      val outer = Var(Option.empty[Signal[String]])
      val inner = Var("initial")

      val inputElement = input(
        tpe := "text",
        cls := "shadow-sm focus:ring-blue-500 focus:border-blue-500 block w-full sm:text-sm border-blue-300 rounded-md bg-blue-50 text-blue-700 placeholder-blue-400 font-mono",
        placeholder := "new value"
      )
      div(
        cls := "space-y-4",
        div(
          inputElement
        ),
        div(
          button(
            cls := "inline-flex items-center px-3 py-2 border border-blue-500 shadow-sm tracking-wide font-medium rounded-md text-blue-100 bg-blue-600 hover:bg-blue-500 hover:text-blue-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500",
            "update inner signal",
            thisEvents(onClick).mapTo(inputElement.ref.value) --> inner.writer
          )
        ),
        div(
          cls := "flex space-x-4",
          button(
            cls := "inline-flex items-center px-3 py-2 border border-blue-500 shadow-sm tracking-wide font-medium rounded-md text-blue-100 bg-blue-600 hover:bg-blue-500 hover:text-blue-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500",
            "put inner signal into outer signal",
            onClick.mapTo(Some(inner.signal)) --> outer.writer
          ),
          button(
            cls := "inline-flex items-center px-3 py-2 border border-blue-500 shadow-sm tracking-wide font-medium rounded-md text-blue-100 bg-blue-600 hover:bg-blue-500 hover:text-blue-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500",
            "remove inner signal from outer signal",
            onClick.mapTo(Option.empty) --> outer.writer
          )
        ),
        div(
          cls := "flex space-x-4 items-center",
          code("inner:"),
          code(
            cls := "text-blue-700 font-medium",
            child.text <-- inner.signal
          )
        ),
        div(
          cls := "flex space-x-4 items-center",
          code("outer:"),
          code(
            cls := "text-blue-700 font-medium",
            child.text <-- outer.signal.map(_.toString())
          )
        ),
        div(
          cls := "flex space-x-4 items-center",
          code("outer.shiftOption:"),
          code(
            cls := "text-blue-700 font-medium",
            /* <focus> */
            child.text <-- outer.signal.shiftOption.map(_.toString())
            /* </focus> */
          )
        )
      )
    })
