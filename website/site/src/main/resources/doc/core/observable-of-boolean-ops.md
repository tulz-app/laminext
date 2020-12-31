```scala
val obs: Observable[Boolean] = ???

obs.cls("my-class") // obs.map(b => Seq("my-class" -> b))

div(
  cls <-- obs.cls("my-class")
)
```
