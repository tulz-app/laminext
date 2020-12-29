package app.tulz.laminext.site.examples

object TestExample
    extends CodeExample("Example 1", "No description")({
// format: off
import com.raquo.laminar.api.L._

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
