package io.laminext.site.examples.util.ex_human_readable_size

import io.laminext.site.examples.CodeExample
import com.yurique.embedded.FileAsString

object HumanReadableSizeExample
    extends CodeExample(
      id = "example-human-readable-size",
      title = "Human Readable Size",
      description = FileAsString("description.md")
    )(() => {
      import com.raquo.laminar.api.L._
      import io.laminext.syntax.core._
      import io.laminext.util.HumanReadableSize

      val inputElement = input(
        tpe         := "number",
        cls         := "shadow-sm focus:ring-blue-500 focus:border-blue-500 block w-full sm:text-sm border-blue-300 rounded-md bg-blue-50 text-blue-700 placeholder-blue-400 font-mono",
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
            cls := "text-blue-700 font-medium",
            child.text <-- inputElement.value.map(HumanReadableSize.format)
          )
        )
      )
    })
