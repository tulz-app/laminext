## `thisEvents({eventProp})`

Allows to use event props like they were streams.

See [example](/core/example-this-events).

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

val anyObserver: Observer[Any] = ???
val stringObserver: Observer[String] = ???
val booleanObserver: Observer[Boolean] = ???
val signalOfString: Signal[String] = ???
def makeAnotherStream(): EventStream[String] = ???

div(
  thisEvents(onClick) --> anyObserver,
  thisEvents(onClick.useCapture) --> anyObserver,
  thisEvents(onClick.useCapture.useBubbleMode) --> anyObserver,
  thisEvents(onClick.useBubbleMode.preventDefault) --> anyObserver,
  thisEvents(onClick.preventDefault.stopPropagation) --> anyObserver,
  // etc

  thisEvents(onClick).map(_ => "string") --> stringObserver,
  thisEvents(onClick).mapTo("string") --> stringObserver,
  thisEvents(onClick).mapToValue("string") --> stringObserver,
  thisEvents(onClick).mapToUnit --> anyObserver,
  thisEvents(onClick).mapToTrue --> booleanObserver,
  thisEvents(onClick).mapToFalse --> booleanObserver,
  thisEvents(onClick).flatMap(_ => makeAnotherStream()) --> stringObserver,
  thisEvents(onClick).withCurrentValueOf(signalOfString).map(_.trim).filterNot(_.isEmpty) --> stringObserver,
  thisEvents(onClick).sample(signalOfString).map(_.trim).filterNot(_.isEmpty) --> stringObserver,
  // most of the stream (and signal) combinators are proxied as well:
  // filter, collect, delay, delaySync, throttle, debounce, foldLeft, foldLeftRecover, startWith, 
  // startWithTry, startWithNone, toSignal, toSignalWithTry, toWeakSignal, combineWith, sample,
  // debug*, ...
)
```

## `.mapToTrue`, `.mapToFalse` and `.mapToUnit`

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

val anyObserver: Observer[Any] = ???
val booleanObserver: Observer[Boolean] = ???

div(
  onClick.mapToTrue -> booleanObserver,
  onClick.mapToFalse -> booleanObserver,
  onClick.mapToUnit -> anyObserver  
)
```
