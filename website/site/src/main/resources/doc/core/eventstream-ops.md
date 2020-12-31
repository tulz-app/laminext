```scala
val s: EventStream[String] = ???
val transitions: EventStream[(Option[String], String)] = s.transitions
```

See [example](/core/signal-transitions) (for Signal).

---

```scala
val stream: EventStream[String] = ???

val failures: EventStream[Throwable] = stream.failures
```

---

```scala
val stream: EventStream[String] = ???

val unitStream:  EventStream[Unit]     = stream.mapToUnit
val trueStream:  EventStream[Boolean]  = stream.mapToTrue
val falseStream: EventStream[Boolean]  = stream.mapToFalse
```

---

```scala
val stream: EventStream[Double] = ???

stream.delayFor(value => value * 1000)
// TODO doc/example
```

---

```scala
val stream: EventStream[String] = ???
val busy: Signal[Boolean] = ???

stream.skipWhen(busy)
// TODO doc/example
```

---

```scala
val stream: EventStream[String] = ???
val ready: Signal[Boolean] = ???

stream.keepWhen(ready)
// TODO doc/example
```

---

```scala
val stream: EventStream[String] = ???
val ready: Signal[Boolean] = ???

stream.drop(10).take(20)
// TODO doc/example
```

---

```scala
val stream: EventStream[String] = ???

div(
  // alias for -->, more IntelliJ friendly
  stream.bind { string => 
    dom.console.log(string)  
  },

  stream.bindCollect {
    case s if s.length < 5 =>
      dom.console.log("it's shorter than 5!")
  }  
)
```
