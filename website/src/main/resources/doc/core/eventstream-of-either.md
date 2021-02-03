## `.isLeft` 
## `.isRight`

`EventStream[Either[L, R]] => EventStream[Boolean]`

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

trait A
trait B

val stream: EventStream[Either[A, B]] = ???

val isLeftStream: EventStream[Boolean] = stream.isLeft
val isRightStream: EventStream[Boolean] = stream.isRight
```

## `.collectRight` 

`EventStream[Either[L, R]] -> EventStream[R]`

## `.collectLeft`

`EventStream[Either[L, R]] => EventStream[L]`

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

trait A
trait B

val stream: EventStream[Either[A, B]] = ???

val lefts: EventStream[A] = stream.collectLeft
val rights: EventStream[B] = stream.collectRight
```

## `.rightMap`

`EventStream[Either[L, R]] => (R => U) => EventStream[Either[L, U]]`

## `.leftMap`

`EventStream[Either[L, R]] => (L => U) => EventStream[Either[U, R]]`

"Changes" the type of the left/right projection keeping the other one as it is.

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

trait L
trait R
trait U

val stream: EventStream[Either[L, R]] = ???
val leftProject: L => U = ???
val rightProject: R => U = ???

val leftMapped: EventStream[Either[U, R]] = stream.leftMap(leftProject)
val rightMapped: EventStream[Either[L, U]] = stream.rightMap(rightProject)
```

## `.eitherT`

Doc TODO

