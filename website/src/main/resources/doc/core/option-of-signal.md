## `.shiftSignal`

`Option[Signal[A]] => Signal[Option[A]]`

Transforms an `Option[Signal[A]]` into a `Signal[Option[A]]`.

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

val option: Option[Signal[String]] = ???

val lifted: Signal[Option[String]] = option.shiftSignal
```
