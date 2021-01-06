package io.laminext.site.examples.signal.ex_shift_option

import io.laminext.site.examples.CodeExample
import com.yurique.embedded.FileAsString

object SignalShiftOptionExample
    extends CodeExample(
      id = "example-signal-shift-option",
      title = "Signal shiftOption",
      description = FileAsString("description.md")
    )(() => {
      import io.laminext.syntax.all._
      import com.raquo.laminar.api.L._

      val outer = Var(Option.empty[Signal[String]])
      val inner = Var("initial")

      val inputElement = input(
        tpe := "text",
        cls := "shadow-sm focus:ring-indigo-500 focus:border-indigo-500 block w-full sm:text-sm border-gray-300 rounded-md",
        placeholder := "new value"
      )
      val updateButton = button(
        cls := "inline-flex items-center px-3 py-2 border border-blue-500 shadow-sm tracking-wide font-medium rounded-md text-blue-100 bg-blue-600 hover:bg-blue-500 hover:text-blue-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500",
        "update inner signal"
      )
      val putButton = button(
        cls := "inline-flex items-center px-3 py-2 border border-blue-500 shadow-sm tracking-wide font-medium rounded-md text-blue-100 bg-blue-600 hover:bg-blue-500 hover:text-blue-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500",
        "put inner signal into outer signal",
        onClick --> { _ =>
          outer.writer.onNext(Some(inner.signal))
        }
      )
      val removeButton = button(
        cls := "inline-flex items-center px-3 py-2 border border-blue-500 shadow-sm tracking-wide font-medium rounded-md text-blue-100 bg-blue-600 hover:bg-blue-500 hover:text-blue-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500",
        "remove inner signal from outer signal",
        onClick --> { _ =>
          outer.writer.onNext(Option.empty)
        }
      )

      div(
        updateButton
          .events(onClick).mapTo(inputElement.ref.value) --> inner.writer,
        cls := "space-y-4",
        div(
          inputElement
        ),
        div(
          updateButton
        ),
        div(
          cls := "flex space-x-4",
          putButton,
          removeButton
        ),
        div(
          cls := "flex space-x-4 items-center",
          code("inner:"),
          code(
            cls := "text-blue-600",
            child.text <-- inner.signal
          )
        ),
        div(
          cls := "flex space-x-4 items-center",
          code("outer:"),
          code(
            cls := "text-blue-600",
            child.text <-- outer.signal.map(_.toString())
          )
        ),
        div(
          cls := "flex space-x-4 items-center",
          code("outer.shiftOption:"),
          code(
            cls := "text-blue-600",
            child.text <-- outer.signal.shiftOption.map(_.toString())
          )
        )
      )
    })
