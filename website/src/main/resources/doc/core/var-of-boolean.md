## `.toggle` and `.toggleObserver`

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

val booleanVar = Var(false)
val streamOfToggleRequests: EventStream[_] = ???

booleanVar.toggle()

div(
  streamOfToggleRequests --> booleanVar.toggleObserver 
)
```

See [example](/core/example-var-of-boolean-toggle).
