package app.tulz.laminext.site.examples.element.ex_input_values

import app.tulz.laminext.site.examples.CodeExample
import app.tulz.website.macros.FileToLiteral

object InputValuesExample
    extends CodeExample(
      id = "input-values",
      title = "Input Values",
      description = FileToLiteral("description.md")
    )(() => {
      import app.tulz.laminar.ext._
      import com.raquo.laminar.api.L._

      val inputElement1 = input(
        tpe := "text",
        cls := "shadow-sm focus:ring-indigo-500 focus:border-indigo-500 block w-full sm:text-sm border-gray-300 rounded-md",
        placeholder := "input 1"
      )
      val inputElement2 = input(
        tpe := "text",
        cls := "shadow-sm focus:ring-indigo-500 focus:border-indigo-500 block w-full sm:text-sm border-gray-300 rounded-md",
        placeholder := "input 2"
      )

      div(
        div(
          "Try tab'ing between inputs, cutting and pasting."
        ),
        div(
          span("input 1:"),
          inputElement1
        ),
        div(
          cls := "flex space-x-1 items-center",
          span("input1.valueSignal:"),
          code(
            cls := "text-blue-600",
            child.text <-- inputElement1.valueSignal
          )
        ),
        div(
          span("input 2:"),
          inputElement2
        ),
        div(
          cls := "flex space-x-1 items-center",
          span("input2.valueSignal:"),
          code(
            cls := "text-blue-600",
            child.text <-- inputElement2.valueSignal
          )
        )
      )
    })
