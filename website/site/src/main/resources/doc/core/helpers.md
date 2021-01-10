## `Seq.join`

Interleaves a `Seq[A]` with elements of `A` (like `.mkString` but returns a `Seq`).

See [example](/core/example-seq-join).

## `whenEmpty` and `whenDefined`

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

## `when` and `whenNot`

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

