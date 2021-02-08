## `.childWhenLeft` 
## `.childWhenRight`

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

val obs: Observable[Either[String, Int]] = ???

div(
  obs.childWhenLeft {
    span("either in signal is left")
  },
  obs.childWhenRight {
    span("either in signal is right")
  }
)
```

## `.doWhenLeft` 
## `.doWhenRight`

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

val obs: Observable[Either[String, Int]] = ???

div(
  obs.doWhenLeft { 
    dom.console.log("either in signal is left")    
  },
  obs.doWhenRight {
    dom.console.log("either in signal is right")
  }
)
```
