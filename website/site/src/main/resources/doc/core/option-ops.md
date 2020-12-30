```scala
val o: Option[String] = ???

div(
  whenEmpty(o) {
    span("o is empty")
  },
  whenDefined(o) {
    span("o is defined")
  },
  o.whenEmpty(
    cls := "class-1"
  ),
  o.whenDefined(
    cls := "class-2"
  )
)
```

