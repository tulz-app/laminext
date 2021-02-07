Themed transitions using the `Transition` from `laminext-ui`.

Theme contains configurations for the following transitions:

* default (in the default theme, it's opacity and scale)
* scale
* opacity
* opacity and scale
* resize

Available via the `TW` object.

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.tailwind._

val showing: Signal[Boolean] = ???

div(
  div(
    TW.transition(showing), // default
  ),
  div(
    TW.transition.opacityAndScale(showing)
  ),
  div(
    TW.transition.opacity(showing)
  ),
  div(
    TW.transition.scale(showing)
  ),
  div(
    TW.transition.resize(showing)
  )
)
```

See the [example](/tailwind/example-transition)
