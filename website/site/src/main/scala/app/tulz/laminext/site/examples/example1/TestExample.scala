package app.tulz.laminext.site.examples.example1

import app.tulz.laminext.site.examples.CodeExample
import app.tulz.website.macros.FileToLiteral
import com.raquo.laminar.api.L._

object TestExample
    extends CodeExample(
      id = "example-1",
      title = "Example 1",
      description = FileToLiteral("description.md")
    )(() => {
// format: off
val counter = Var(1)

div(
  cls := "space-y-1",
  div(
    cls := "flex space-x-1 items-center",
    span("Counter: "),
    span(
      child.text <-- counter.signal.map(_.toString)
    )
  ),
  div(
    button(
      onClick --> { _ => counter.update(_ + 1) },
      "increase"
    )
  )
)
// format: on
    })
