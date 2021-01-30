package io.laminext.site.examples.tailwind.ex_tailwind_button_sizes

import com.yurique.embedded.FileAsString
import io.laminext.site.examples.CodeExample

object TailwindButtonSizesExample
    extends CodeExample(
      id = "example-button-sizes",
      title = "Button Sizes",
      description = FileAsString("description.md")
    )(() => {
      import com.raquo.laminar.api.L._
      import io.laminext.syntax.tailwind._

      div(
        cls := "p-4 space-y-4 bg-white",
        div(
          cls := "space-x-4",
          button.btn.tiny.outline.blue("tiny.outline.blue"),
          button.btn.xs.outline.blue("xs.outline.blue"),
          button.btn.sm.outline.blue("sm.outline.blue"),
          button.btn.md.outline.blue("md.outline.blue"),
          button.btn.lg.outline.blue("lg.outline.blue"),
          button.btn.xl.outline.blue("xl.outline.blue")
        ),
        div(
          cls := "space-x-4",
          button.btn.tiny.fill.blue("tiny.fill.blue"),
          button.btn.xs.fill.blue("xs.fill.blue"),
          button.btn.sm.fill.blue("sm.fill.blue"),
          button.btn.md.fill.blue("md.fill.blue"),
          button.btn.lg.fill.blue("lg.fill.blue"),
          button.btn.xl.fill.blue("xl.fill.blue")
        )
      )
    })
