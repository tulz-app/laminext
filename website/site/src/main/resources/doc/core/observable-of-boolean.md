## `.cls` and `.clsNot` 

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.all._

val obs: Observable[Boolean] = ???

div(
  obs.cls("class-1"),    // cls <-- obs.map(b => Seq("class-1" -> b))
  obs.clsNot("class-2"), // cls <-- obs.map(b => Seq("class-2" -> !b))
)
```

## `.classSwitch`

See [example](/core/example-signal-of-boolean-class-switch).

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.all._

val obs: Observable[Boolean] = ???

div(
  obs.classSwitch(
    whenTrue = "class-1",
    whenFalse = "class-2",
  ) // // cls <-- obs.map(b => Seq("class-1" -> b, "class-2" -> !b))
)
```

## `.childWhenTrue` and `.childWhenFalse`

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.all._

val obs: Observable[Boolean] = ???

div(
  obs.childWhenTrue {
    span("signal is true")
  },
  obs.childWhenFalse {
    span("signal is false")
  }
)
```

## `.doWhenTrue` and `.doWhenFalse`

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.all._

val obs: Observable[Boolean] = ???

div(
  obs.doWhenTrue { () =>
    dom.console.log("signal is true")    
  },
  obs.doWhenFalse { () =>
    dom.console.log("signal is false")
  }
)
```
