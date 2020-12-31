```scala
val o: Option[Signal[String]] = ???

val lifted: Signal[Option[String]] = o.lift
```
