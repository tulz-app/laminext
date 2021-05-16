Transition configs using the TailwindCSS utilities.

* `TailwindTransition.scale`
* `TailwindTransition.opacity`
* `TailwindTransition.opacityAndScale`
* `TailwindTransition.resize`


```scala
import com.raquo.laminar.api.L._
import io.laminext.ui._
import io.laminext.syntax.tailwind._

val showing: Signal[Boolean] = ???

div(
  div(
    addTransition(showing, TailwindTransition.scale)
  ),
  div(
    addTransition(showing, TailwindTransition.opacity)
  ),
  div(
    addTransition(showing, TailwindTransition.opacityAndScale)
  ),
  div(
    addTransition(showing, TailwindTransition.resize)
  )
)
```
