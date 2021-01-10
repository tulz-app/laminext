See [example](/core/example-signal-of-option).

## `.isDefined` and `.isEmpty`

`Signal[Option[A]] -> Signal[Boolean]`

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.all._

trait A

val signal: Signal[Option[A]] = ???
val signalIsEmpty: Signal[Boolean] = signal.isEmpty
val signalIsDefined: Signal[Boolean] = signal.isDefined
```

## `.optionContains`

`Signal[Option[A]] -> Signal[Boolean]`

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.all._

val signal: Signal[Option[String]] = ???
val containsHello: Signal[Boolean] = signal.optionContains("hello")
```

## `.optionExists`

`Signal[Option[A]] -> Signal[Boolean]`

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.all._

val signal: Signal[Option[String]] = ???
val containsHello: Signal[Boolean] = signal.optionExists(_.toLowerCase == "hello")
```

## `.optionMap`

`Signal[Option[A]] -> Signal[Option[B]]`

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.all._

val signal: Signal[Option[String]] = ???
val mapped: Signal[Option[Int]] = signal.optionMap(_.length)
```

## `.optionFlatMap`

`Signal[Option[A]] -> Signal[Option[B]]`

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.all._

val signal: Signal[Option[String]] = ???
val flatMapped: Signal[Option[Int]] = signal.optionFlatMap(string => Option(string).filter(_.length > 5).map(_.length))
```


## `.withDefault`

`Signal[Option[A]] -> Signal[A]`

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.all._

val signal: Signal[Option[String]] = ???
val withDefault: Signal[String] = signal.withDefault("I'm the default!")
```


## `.someFlatMap`

`Signal[Option[A]] -> Signal[Option[B]]`

Unlike the previous functions, this one takes a function that returns another `Signal[...]`.

"Changes" the type of the left/right projection keeping the other one as it is.

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.all._

trait A
trait B

val signal: Signal[Option[A]] = ???
val project: A => Signal[B] = ???

val flatMapped: Signal[Option[U]] = signal.someFlatMap(project)
```

> TODO: this is not consistent with `.leftFlatMap`/`.rightFlatMap` for eithers – those take a projection that returns
> an `Either[..., ...]`, not a plain value like here.
