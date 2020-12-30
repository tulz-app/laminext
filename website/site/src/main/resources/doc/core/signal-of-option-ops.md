See [example](/core/signal-of-option).


```scala
val s1: Signal[Option[String]] = ???

div(
  whenEmpty(s1) {
    span("s1 is empty")
  },
  whenDefined(s1) {
    span("s1 is defined")
  },
  s1.childWhenEmpty {
    span("s1 is empty")
  },
  s1.childWhenDefined {
    span("s1 is defined")
  }
)
```
