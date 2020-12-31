```scala
val s: Signal[Option[Signal[String]]] = ???

val shifted: Signal[Option[String]] = s.shiftOption
```

See [example](/core/example-signal-shift-option).
