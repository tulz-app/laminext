```scala
val o: Option[String] = ???

div(
  whenEmpty(o) {
    span("o is empty")
  },
  whenDefined(o) {
    span("o is defined")
  }
)
```

