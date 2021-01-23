## `.bind` and `.bindCollect`

`.bind` is an alias for `-->(onNext: A => Unit)`.

`.bindCollect` is the same, but checks if the provided partial function is defined at the value.

```scala
import org.scalajs.dom
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

val obs: Observable[String] = ???

div(
  obs.bind { string => 
    dom.console.log(string)  
  },
  obs.bindCollect {
    case s if s.length < 5 =>
      dom.console.log("it's shorter than 5!")
  }  
)
```
