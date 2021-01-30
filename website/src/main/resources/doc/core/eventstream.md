## `.transitions`

`EventStream[A] => EventStream[(Option[A], A)]`

Get an `EventStream[(Option[A], A)]` of tuples of (previous, latest) emitted value.

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._
val stream: EventStream[String] = ???
val transitions: EventStream[(Option[String], String)] = stream.transitions
```

See the [example](/core/example-signal-transitions) for Signal `.transitions`.

## `.previousAndCurrent`

`EventStream[A] => EventStream[(Option[A], Option[A])]`

Returns a `Signal[(Option[A], Option[A])]` of tuples of optional (previous, latest) emitted values.

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._
val stream: EventStream[String] = ???
val transitions: EventStream[(Option[String], Option[String])] = stream.previousAndCurrent
```

## `.previousAndLatestWithReset`

Behaves like the `.previousAndLatest` but accepts an additional parameter – `reset: EventStream[Any]`.

When the `reset` stream emits, the "latest" value becomes `None`.

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

val stream: EventStream[String] = ???
val resets: EventStream[_] = ???

val transitions: EventStream[(Option[String], Option[String])] = stream.previousAndLatestWithReset(resets)
```

## `.mapToUnit` 
## `.mapToTrue` 
## `.mapToFalse`

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

val stream: EventStream[String] = ???

val units: EventStream[Unit] = stream.mapToUnit
val trues: EventStream[Boolean] = stream.mapToTrue
val falses: EventStream[Boolean] = stream.mapToFalse
```

## `.delayFor`

`EventStream[A] => (A => FiniteDuration) => EventStream[A]`

Similar to the built-in `.delay`, but the delay is derived from the value emitted by the stream.

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._
import scala.concurrent.duration._

val stream: EventStream[Int] = ???

val delayed: EventStream[Int] = stream.delayFor(_.seconds)
```

## `.skipWhen`
## `.keepWhen`

`EventStream[A] => Signal[Boolean] => EventStream[A]`

These functions accept a `Signal[Boolean]` and the resulting stream will emit/skip the events if the sampled
value of the signal is `true`.

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

val stream: EventStream[Int] = ???
val booleanSignal: Signal[Boolean] = ???

val skip: EventStream[Int] = stream.skipWhen(booleanSignal)
val keep: EventStream[Int] = stream.keepWhen(booleanSignal)
```

## `.drop` 
## `.take`

`EventStream[A] => Int => EventStream[A]`

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

val stream: EventStream[Int] = ???

val first10: EventStream[Int] = stream.take(10)
val withoutFirst10: EventStream[Int] = stream.drop(10)
```

## `.distinct`

`EventStream[A] => EventStream[A]`

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

val stream: EventStream[Int] = ???

val distinctNumbers: EventStream[Int] = stream.distinct
```

## `.flip`

Returns a `Signal[Boolean]` with the specified initial value.

Whenever the stream emits an `on` event, the signal's value is set to `true`. 
When the `off` event is emitted, the signal's value is set to `false`.

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

sealed trait MyEvent
case object OnEvent extends MyEvent
case object OffEvent extends MyEvent

val stream: EventStream[MyEvent] = ???

val signal: Signal[Boolean] = stream.flip(
  on = OnEvent,
  off = OffEvent,
  intial = true  
)
```

An overloaded version accepts a `PartialFunction[A, Boolean]` and sets the signal's value to the value returned
by that function whenever this stream emits (if the function is `definedAt`).

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

case class OnOffEvent(on: Boolean) 

val stream: EventStream[OnOffEvent] = ???

val signal: Signal[Boolean] = stream.flip(
  {
    case OnOffEvent(on) => on
  }, 
  intial = true  
)
```
