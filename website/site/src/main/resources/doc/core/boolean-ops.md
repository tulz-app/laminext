```scala
val b: Boolean = ???

div(
  when(b) {
    span("b is true")
  },
  whenNot(b) {
    span("b is false")
  },
  b.whenTrue(
    cls := "class-1"
  ),
  b.whenFalse(
    cls := "class-2"
  )
)
```

