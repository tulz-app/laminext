## `.isDefined` 
## `.isEmpty`

`EventStream[Option[A]] => EventStream[Boolean]`

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

trait A

val stream: EventStream[Option[A]] = ???

val isDefinedStream: EventStream[Boolean] = stream.isDefined
val isEmptyStream: EventStream[Boolean] = stream.isEmpty
```

## `.collectDefined`

`EventStream[Option[A]] => EventStream[A]`

When the underlying stream emits a `Some`, this emits the value inside that `Some`. 

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

trait A

val stream: EventStream[Option[A]] = ???

val transitions: EventStream[A] = stream.collectDefined
```

## `.optionCollect`

`EventStream[Option[A]] => (A => B) =>  EventStream[B]`

When the underlying stream emits a `Some`, this emits the value returned by
the provided `PartialFunction` (if it is `definedAt`). 

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

val stream: EventStream[Option[String]] = ???

val transitions: EventStream[Int] = stream.collectOption {
  case s if s.length > 5 => s.length
}
```

## `.optionContains` 

`EventStream[Option[A]] => A => EventStream[Boolean]`

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

val stream: EventStream[Option[String]] = ???

val containsHello: EventStream[Boolean] = stream.optionContains("hello")
```

## `.optionExists` 

`EventStream[Option[A]] => (A => Boolean) => EventStream[Boolean]`

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

val stream: EventStream[Option[String]] = ???

val longerThan5: EventStream[Boolean] = stream.optionExists(_.length > 5)
```

## `.optionMap`

`EventStream[Option[A]] => (A => B) => EventStream[Option[B]]`

Map the values inside the emitted `Option`s.

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

val stream: EventStream[Option[String]] = ???

val upperCased: EventStream[Option[Boolean]] = stream.optionMap(_.toUpperCase)
```

## `.optionFlatMap`

`EventStream[Option[A]] => (A => Option[B]) => EventStream[Option[B]]`

Same for the `.flatMap`.

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

val stream: EventStream[Option[String]] = ???

val upperCasedAndLongerThan5: EventStream[Option[String]] = 
  stream.optionFlatMap(string => Option(string).filter(_.length > 5).map(_.toUpperCase))
```

## `.withDefault`

`EventStream[Option[A]] => A => EventStream[A]`

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

val stream: EventStream[Option[String]] = ???

val withDefault: EventStream[String] = stream.withDefault("I'm the default!")
```

## `.optionT`

Doc TODO
