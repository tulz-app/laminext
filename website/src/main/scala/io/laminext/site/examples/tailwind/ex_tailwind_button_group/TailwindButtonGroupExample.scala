package io.laminext.site.examples.tailwind.ex_tailwind_button_group

import com.yurique.embedded.FileAsString
import io.laminext.site.examples.CodeExample

object TailwindButtonGroupExample
    extends CodeExample(
      id = "example-button-group",
      title = "Button Group",
      description = FileAsString("description.md")
    )(() => {
      import com.raquo.laminar.api.L._
      import io.laminext.syntax.tailwind._

      div(
        cls := "p-4 bg-white space-y-4",
        div.buttonGroup(
          button.btn.group.first.lg.fill.blue("group.first.fill.blue"),
          button.btn.group.inner.lg.fill.blue("group.inner.fill.blue"),
          button.btn.group.last.lg.fill.blue("group.last.fill.blue")
        )
      )
    })
