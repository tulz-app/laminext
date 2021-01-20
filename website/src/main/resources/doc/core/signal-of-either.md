## `.isLeft` and `.isRight`

`Signal[Either[L, R]] -> Signal[Boolean]`

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.all._

trait L
trait R

val signal: Signal[Either[L, R]] = ???

val isLeft: Signal[Boolean] = signal.isLeft
val isRight: Signal[Boolean] = signal.isRight
```

## `.eitherRightMap` and `.eitherLeftMap`

`Signal[Either[L, R]] -> Signal[Either[U, R]] / Signal[Either[L, U]]`

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.all._

trait L
trait R
trait U

val signal: Signal[Either[L, R]] = ???

val projectLeft: L => U = ???
val projectRight: R => U = ???

val leftMapped: Signal[Either[U, R]] = signal.leftMap(projectLeft)
val rightMapped: Signal[Either[L, U]] = signal.leftMap(projectRight)
```

## `.eitherToOption` and `.eitherLeftToOption`

`Signal[Either[L, R]] -> Signal[Option[L]] / Signal[Option[R]]`

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.all._

trait L
trait R

val signal: Signal[Either[L, R]] = ???

val toOption: Signal[Option[L]] = signal.eitherToOption
val leftToOption: Signal[Option[R]] = signal.eitherLeftToOption
```

## `.maybeRightMap` and `.maybeLeftMap`

`Signal[Either[L, R]] -> Signal[Option[U]]`

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.all._

trait L
trait R
trait U

val signal: Signal[Either[L, R]] = ???

val projectLeft: L => U = ???
val projectRight: R => U = ???

val maybeRightMapped: Signal[Option[U]] = signal.maybeRightMap(projectRight)
val maybeLeftMapped: Signal[Option[U]] = signal.maybeLeftMap(projectLeft)
```

## `.eitherFold`

`Signal[Either[L, R]] -> Signal[U]`


```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.all._

trait L
trait R

val projectLeft: L => U = ???
val projectRight: R => U = ???

val signal: Signal[Either[L, R]] = ???

val folded: Signal[U] = signal.eitherFold(projectLeft, projectRight)
```

## `.eitherSwap`

`Signal[Either[L, R]] -> Signal[Either[R, L]]`

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.all._

trait L
trait R

val signal: Signal[Either[L, R]] = ???

val swapped: Signal[Either[R, L]] = signal.eitherSwap
```

## `.rightFlatMap` and `.leftFlatMap`

Unlike the previous functions, these take a function that returns another `Signal[Either[..., ...]]`.

`.rightFlatMap` "changes" the type of the right projection of the original `Either`, preserving the type of the left projection.
`.leftFlatMap` is vice-versa.

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.all._

trait L
trait R
trait U

val signal: Signal[Either[L, R]] = ???
val leftTransform: L => Signal[Either[U, R]] = ???
val rightTransform: R => Signal[Either[L, U]] = ???

val leftFlatMapped: Signal[Either[U, R]] = signal.leftFlatMap(leftTransform)
val rightFlatMapped: Signal[Either[L, U]] = signal.rightFlatMap(rightTransform)
```
