```scala
val b: Boolean = ???

div(
  when(b) {
    span("b is true")
  },
  whenNot(b) {
    span("b is false")
  }
)
```

