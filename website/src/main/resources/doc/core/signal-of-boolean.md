## `&&`, `||` and `!`  

`Signal[Boolean] -> Signal[Boolean]`

Boolean operations.

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.all._

val s1: Signal[Boolean] = ???
val s2: Signal[Boolean] = ???

val sOr: Signal[Boolean]  = s1 || s2
val sAnd: Signal[Boolean] = s1 && s2
val sNot: Signal[Boolean] = s1.not
val sNeg: Signal[Boolean] = !s1

val sOrBoolean: Signal[Boolean]  = s1 || true
val sAndBoolean: Signal[Boolean] = s1 && false
```

## `.switch`

`Signal[Boolean] -> Signal[U]`

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.all._

val signal: Signal[Boolean] = ???

val switched: Signal[String] = signal.switch(
  whenTrue = "I'm true",
  whenFalse = "I'm false"
)
```

> TODO: possible to define for `Observable[Boolean]` instead?
