## `.isLeft` 
## `.isRight`

`Signal[Either[L, R]] => Signal[Boolean]`

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

trait L
trait R

val signal: Signal[Either[L, R]] = ???

val isLeft: Signal[Boolean] = signal.isLeft
val isRight: Signal[Boolean] = signal.isRight
```

## `.rightMap`

`Signal[Either[L, R]] => (R => U) => Signal[Either[L, U]]`

## `.leftMap`

`Signal[Either[L, R]] => (L => U) => Signal[Either[U, R]]`

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

trait L
trait R
trait U

val signal: Signal[Either[L, R]] = ???

val projectLeft: L => U = ???
val projectRight: R => U = ???

val leftMapped: Signal[Either[U, R]] = signal.leftMap(projectLeft)
val rightMapped: Signal[Either[L, U]] = signal.rightMap(projectRight)
```


## `.eitherT`

Doc TODO
