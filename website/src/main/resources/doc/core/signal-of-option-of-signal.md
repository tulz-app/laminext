## `.shiftOption`

`Signal[Option[Signal[A]]] => Signal[Option[A]]`

Transforms a `Signal[Option[Signal[A]]]` into a `Signal[Option[A]]`

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

val signal: Signal[Option[Signal[String]]] = ???

val shifted: Signal[Option[String]] = signal.shiftOption
```

See [example](/core/example-signal-shift-option).
