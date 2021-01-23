## `.transitions`

`Signal[A] -> Signal[(Option[A], A)]`

Get a signal of tuples: previous value (optional) and the current value. 

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

val signal: Signal[String] = ???

val transitions: Signal[(Option[String], String)] = signal.transitions
```

See [example](/core/example-signal-transitions).

## `.valueIs`

`Signal[A] -> Signal[Boolean]`

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

val signal: Signal[String] = ???

val valueIsHello: Signal[Boolean] = signal.valueIs("hello")
```

## `.contains`

`Signal[A] -> Signal[Boolean]`

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

val signal: Signal[String] = ???

val startsWithHello: Signal[Boolean] = signal.contains(_.startsWith("hello"))
```
