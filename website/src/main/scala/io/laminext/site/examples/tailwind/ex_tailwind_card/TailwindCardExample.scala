package io.laminext.site.examples.tailwind.ex_tailwind_card

import com.yurique.embedded.FileAsString
import io.laminext.site.examples.CodeExample

object TailwindCardExample
    extends CodeExample(
      id = "example-card",
      title = "Card",
      description = FileAsString("description.md")
    )(() => {
      import com.raquo.laminar.api.L._
      import io.laminext.syntax.tailwind._

      div(
        cls := "p-4 bg-white",
        div.card.wrap(
          div.card.header(
            div.card.title("header")
          ),
          div.card.body("body"),
          div.card.footer("footer")
        )
      )
    })
