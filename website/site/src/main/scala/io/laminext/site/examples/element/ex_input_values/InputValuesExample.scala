package io.laminext.site.examples.element.ex_input_values

import io.laminext.site.examples.CodeExample
import com.yurique.embedded.FileAsString

object InputValuesExample
    extends CodeExample(
      id = "example-input-values",
      title = "Input Values",
      description = FileAsString("description.md")
    )(() => {
      import io.laminext.syntax.all._
      import com.raquo.laminar.api.L._

      val inputElement1 = input(
        tpe := "text",
        cls := "shadow-sm focus:ring-blue-500 focus:border-blue-500 block w-full sm:text-sm border-blue-300 rounded-md bg-blue-50 text-blue-700 placeholder-blue-400 font-mono",
        placeholder := "input 1"
      )
      val inputElement2 = input(
        tpe := "text",
        cls := "shadow-sm focus:ring-blue-500 focus:border-blue-500 block w-full sm:text-sm border-blue-300 rounded-md bg-blue-50 text-blue-700 placeholder-blue-400 font-mono",
        placeholder := "input 2"
      )
      val inputElement3 = input(tpe := "checkbox")

      div(
        cls := "space-y-4",
        div(
          cls := "text-indigo-700",
          "Try tab'ing between inputs, cutting and pasting."
        ),
        div(
          div(
            code("input 1:")
          ),
          inputElement1
        ),
        div(
          cls := "flex space-x-1 items-center",
          code("input1.value:"),
          code(
            cls := "text-blue-700 font-medium",
            /* <focus> */
            child.text <-- inputElement1.value
            /* </focus> */
          )
        ),
        div(
          div(
            code("input 2:")
          ),
          inputElement2
        ),
        div(
          cls := "flex space-x-1 items-center",
          code("input2.value:"),
          code(
            cls := "text-blue-700 font-medium",
            /* <focus> */
            child.text <-- inputElement2.value
            /* </focus> */
          )
        ),
        div(
          label(
            cls := "flex items-center space-x-2",
            span("input 3"),
            inputElement3
          )
        ),
        div(
          cls := "flex space-x-1 items-center",
          code("input3.checked:"),
          code(
            cls := "text-blue-700 font-medium",
            /* <focus> */
            child.text <-- inputElement3.checked.map(_.toString)
            /* </focus> */
          )
        )
      )
    })
