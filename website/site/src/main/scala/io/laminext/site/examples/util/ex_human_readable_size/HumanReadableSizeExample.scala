package io.laminext.site.examples.util.ex_human_readable_size

import io.laminext.site.examples.CodeExample
import app.tulz.website.macros.FileToLiteral

object HumanReadableSizeExample
    extends CodeExample(
      id = "example-human-readable-size",
      title = "Human Readable Size",
      description = FileToLiteral("description.md")
    )(() => {
      import com.raquo.laminar.api.L._
      import io.laminext.syntax.all._
      import io.laminext.util.HumanReadableSize

      val inputElement = input(
        tpe := "number",
        cls := "shadow-sm focus:ring-indigo-500 focus:border-indigo-500 block w-full sm:text-sm border-gray-300 rounded-md",
        placeholder := "new value"
      )

      div(
        cls := "space-y-4",
        div(
          inputElement
        ),
        div(
          cls := "flex space-x-4 items-center",
          code("human readable:"),
          code(
            cls := "text-blue-600",
            child.text <-- inputElement.valueSignal.map(HumanReadableSize.format)
          )
        )
      )
    })
