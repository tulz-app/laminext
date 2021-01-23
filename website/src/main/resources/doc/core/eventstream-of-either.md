## `.isLeft` and `.isRight`

`EventStream[Either[A, B]] -> EventStream[Boolean]`

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

trait A
trait B

val stream: EventStream[Either[A, B]] = ???

val isLeftStream: EventStream[Boolean] = stream.isLeft
val isRightStream: EventStream[Boolean] = stream.isRight
```

## `.collectRights` and `.collectLefts`

`EventStream[Either[A, B]] -> EventStream[A] / EventStream[B]`

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

trait A
trait B

val stream: EventStream[Either[A, B]] = ???

val lefts: EventStream[A] = stream.collectLefts
val rights: EventStream[B] = stream.collectRights
```

## `.rightMap` and `.leftMap`

`EventStream[Either[L, R]] -> EventStream[Either[L, U]] / EventStream[Either[U, R]]`

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

## `.rightMapC` and `.leftMapC`

TODO: naming (`C` – for "collect")

`EventStream[Either[L, R]] -> EventStream[Either[L, U]] / EventStream[Either[U, R]]`

"Changes" the type of the left/right projection keeping the other one as it is, emits only if the provided function is `definedAt`.

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

trait L
trait R
trait U

val stream: EventStream[Either[L, R]] = ???
val leftProjectC: PartialFunction[L, U] = ???
val rightProjectC: PartialFunction[R, U] = ???

val leftMapped: EventStream[Either[U, R]] = stream.leftMapC(leftProjectC)
val rightMapped: EventStream[Either[L, U]] = stream.rightMapC(rightProjectC)
```

## `.eitherFold`

`EventStream[Either[L, R]] -> EventStream[U]`

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

trait L
trait R
trait U

val stream: EventStream[Either[L, R]] = ???
val leftProject: PartialFunction[L, U] = ???
val rightProject: PartialFunction[R, U] = ???

val folded: EventStream[U] = stream.eitherFold(leftProject, rightProject)
```

## `.eitherSwap`

`EventStream[Either[L, R]] -> EventStream[Either[R, L]]`

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

trait L
trait R

val stream: EventStream[Either[L, R]] = ???

val swapped: EventStream[Either[R, L]] = stream.eitherSwap
```

## `.rightFlatMap` and `.leftFlatMap`

`EventStream[Either[L, R]] -> EventStream[Either[L, U]] / EventStream[Either[U, R]]`

Unlike the previous functions, these take a function that returns another `EventStream[Either[..., ...]]`.

"Changes" the type of the left/right projection keeping the other one as it is.

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

trait L
trait R
trait U

val stream: EventStream[Either[L, R]] = ???
val leftProject: L => EventStream[Either[U, R]] = ???
val rightProject: R => EventStream[Either[L, U]] = ???

val leftMapped: EventStream[Either[U, R]] = stream.leftFlatMap(leftProject)
val rightMapped: EventStream[Either[L, U]] = stream.rightFlatMap(rightProject)
```
