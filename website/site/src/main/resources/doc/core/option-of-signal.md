## `.lift`

Transforms an `Option[Signal[A]]` into a `Signal[Option[A]]`.

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.all._

val option: Option[Signal[String]] = ???

val lifted: Signal[Option[String]] = option.lift
```
