Allows to use event props like they were streams.

See [example](/core/eventprop-stream).

```scala
div(
  onClick.stream --> observer,
  onClick.stream.useCapture --> observer,
  onClick.stream.useCapture.useBubbleMode --> observer,
  onClick.stream.useBubbleMode.preventDefault --> observer,
  onClick.stream.preventDefault.stopPropagation --> observer,
  // etc
  
  onClick.stream.map(_ => "string") --> observer,
  onClick.stream.mapTo("string") --> observer,
  onClick.stream.mapToValue("string") --> observer,
  onClick.stream.mapToUnit --> observer,
  onClick.stream.mapToTrue --> observer,
  onClick.stream.mapToFalse --> observer,
  onClick.stream.flatMap(_ => makeAnotherStream()) --> observer,
  onClick.stream.withCurrentValueOf(signalOfString).map(_.trim).filterNot(_.isEmpty) --> observer,
  // most of the stream (and signal) combinators are proxied as well:
  // filter, collect, delay, delaySync, throttle, debounce, foldLeft, foldLeftRecover, startWith, 
  // startWithTry, startWithNone, toSignal, toSignalWithTry, toWeakSignal, combineWith, sample,
  // debugLog*, ...
)
```

Also:

```scala
div(
  onClick.mapToTrue -> observer,
  onClick.mapToFalse -> observer,
  onClick.mapToUnit -> observer  
)
```
