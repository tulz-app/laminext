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

## `.eitherRightMap` 
## `.eitherLeftMap`

`Signal[Either[L, R]] => (R => U) => Signal[Either[L, U]]`

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
val rightMapped: Signal[Either[L, U]] = signal.leftMap(projectRight)
```

## `.eitherToOption` 
## `.eitherLeftToOption`

`Signal[Either[L, R]] => Signal[Option[R]]`

`Signal[Either[L, R]] => Signal[Option[L]]`

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

trait L
trait R

val signal: Signal[Either[L, R]] = ???

val toOption: Signal[Option[L]] = signal.eitherToOption
val leftToOption: Signal[Option[R]] = signal.eitherLeftToOption
```

## `.eitherFold`

`Signal[Either[L, R]] => (L => U, R => U) => Signal[U]`


```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

trait L
trait R

val projectLeft: L => U = ???
val projectRight: R => U = ???

val signal: Signal[Either[L, R]] = ???

val folded: Signal[U] = signal.eitherFold(projectLeft, projectRight)
```

## `.eitherSwap`

`Signal[Either[L, R]] => Signal[Either[R, L]]`

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

trait L
trait R

val signal: Signal[Either[L, R]] = ???

val swapped: Signal[Either[R, L]] = signal.eitherSwap
```

## `.flatMapWhenRight` 

`Signal[Either[L, R]] => (R => Signal[Either[L, U]]) => Signal[Either[L, U]]`

## `.flatMapWhenLeft`

`Signal[Either[L, R]] => (L => Signal[Either[U, R]]) => Signal[Either[U, R]]`

Unlike the previous functions, these take a function that returns another `Signal[Either[..., ...]]`.

`.flatMapWhenRight` "changes" the type of the right projection of the original `Either`, preserving the type of the left projection.
`.flatMapWhenLeft` is vice-versa.

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

trait L
trait R
trait U

val signal: Signal[Either[L, R]] = ???
val leftProject: L => Signal[Either[U, R]] = ???
val rightProject: R => Signal[Either[L, U]] = ???

val leftFlatMapped: Signal[Either[U, R]] = signal.flatMapWhenLeft(leftProject)
val rightFlatMapped: Signal[Either[L, U]] = signal.flatMapWhenRight(rightProject)
```
