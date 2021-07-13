## `.classSwitch`

See [example](/core/example-signal-of-boolean-class-switch).

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

val obs: Observable[Boolean] = ???

div(
  obs.classSwitch(
    whenTrue = "class-1",
    whenFalse = "class-2"
  ) // cls <-- obs.map(b => Seq("class-1" -> b, "class-2" -> !b))
)
```

## `.childWhenTrue` 
## `.childWhenFalse`

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

val obs: Observable[Boolean] = ???

div(
  obs.childWhenTrue {
    span("source emitted true")
  },
  obs.childWhenFalse {
    span("source emitted false")
  }
)
```

## `.doWhenTrue`
## `.doWhenFalse`

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

val obs: Observable[Boolean] = ???

div(
  obs.doWhenTrue { 
    dom.console.log("source emitted true")
  },
  obs.doWhenFalse {
    dom.console.log("source emitted false")
  }
)
```
