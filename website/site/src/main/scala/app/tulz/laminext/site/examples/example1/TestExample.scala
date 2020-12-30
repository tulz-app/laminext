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
    cls := "flex space-x-4 items-center",
    code("Counter: "),
    code(
      cls := "text-blue-600",
      child.text <-- counter.signal.map(_.toString)
    )
  ),
  div(
    button(
      cls := "inline-flex items-center px-3 py-2 border border-blue-200 shadow-sm text-sm leading-4 font-medium rounded-md text-blue-700 bg-white hover:bg-blue-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500",
      onClick --> { _ => counter.update(_ + 1) },
      "increase"
    )
  )
)
// format: on
    })
