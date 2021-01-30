## `.childWhenDefined` 
## `.childWhenEmpty`

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

val signal: Signal[Option[String]] = ???

div(
  signal.childWhenDefined {
    span("option in signal is defined")
  },
  signal.childWhenEmpty {
    span("option in signal is empty")
  }
)
```

## `.doWhenDefined` 
## `.doWhenEmpty`

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

val signal: Signal[Option[String]] = ???

div(
  signal.doWhenDefined { () =>
    dom.console.log("option in signal is defined")    
  },
  signal.doWhenEmpty { () =>
    dom.console.log("option in signal is empty")
  }
)
```

