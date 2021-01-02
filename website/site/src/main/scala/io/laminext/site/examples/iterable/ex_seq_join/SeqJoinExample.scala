package io.laminext.site.examples.iterable.ex_seq_join

import io.laminext.site.examples.CodeExample
import com.yurique.embedded.FileAsString

object SeqJoinExample
    extends CodeExample(
      id = "example-seq-join",
      title = "Seq Join",
      description = FileAsString("description.md")
    )(() => {
      import io.laminext.syntax.all._
      import com.raquo.laminar.api.L._

      val spans1 = (1 to 6).map(_.toString).map(span(_))
      val spans2 = (1 to 6).map(_.toString).map(span(_))

      div(
        cls := "space-y-4",
        div(
          div(
            code("spans1: ")
          ),
          div(
            cls := "space-x-4",
            spans1
          )
        ),
        div(
          div(
            code("spans2.join(span(\"—\")): ")
          ),
          div(
            cls := "space-x-4",
            spans2.join(() => span("—"))
          )
        )
      )
    })
