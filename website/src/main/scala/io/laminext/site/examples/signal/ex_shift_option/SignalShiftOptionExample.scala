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
        placeholder := "new value"
      )
      div(
        cls := "space-y-4",
        div(
          inputElement
        ),
        div(
          button(
            cls := "btn-md-fill-blue",
            "update inner signal",
            thisEvents(onClick).mapTo(inputElement.ref.value) --> inner.writer
          )
        ),
        div(
          cls := "flex space-x-4",
          button(
            cls := "btn-md-fill-blue",
            "put inner signal into outer signal",
            onClick.mapTo(Some(inner.signal)) --> outer.writer
          ),
          button(
            cls := "btn-md-fill-blue",
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
