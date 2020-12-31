Just sugar to skip `_ =>` in  `stream.flatMap(_ => ...)`

```scala
val stream: EventStream[Unit] = ???

def makeAnotherStream: EventStream[String] = ???

val output: EventStream[String] = stream.flatMapTo(makeAnotherStream)
```
