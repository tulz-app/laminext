See [example](/core/example-signal-of-option).

## `.isDefined` 
## `.isEmpty`

`Signal[Option[A]] => Signal[Boolean]`

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

trait A

val signal: Signal[Option[A]] = ???
val signalIsEmpty: Signal[Boolean] = signal.isEmpty
val signalIsDefined: Signal[Boolean] = signal.isDefined
```

## `.optionContains`

`Signal[Option[A]] => A => Signal[Boolean]`

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

val signal: Signal[Option[String]] = ???
val containsHello: Signal[Boolean] = signal.optionContains("hello")
```

## `.optionExists`

`Signal[Option[A]] => (A => Boolean) => Signal[Boolean]`

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

val signal: Signal[Option[String]] = ???
val containsHello: Signal[Boolean] = signal.optionExists(_.toLowerCase == "hello")
```

## `.optionMap`

`Signal[Option[A]] => (A => B) => Signal[Option[B]]`

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

val signal: Signal[Option[String]] = ???
val mapped: Signal[Option[Int]] = signal.optionMap(_.length)
```

## `.optionFlatMap`

`Signal[Option[A]] => (A => Option[B]) => Signal[Option[B]]`

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

val signal: Signal[Option[String]] = ???
val flatMapped: Signal[Option[Int]] = signal.optionFlatMap(string => Option(string).filter(_.length > 5).map(_.length))
```

## `.withDefault`

`Signal[Option[A]] => A => Signal[A]`

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

val signal: Signal[Option[String]] = ???
val withDefault: Signal[String] = signal.withDefault("I'm the default!")
```

## `.optionT`

Doc TODO
