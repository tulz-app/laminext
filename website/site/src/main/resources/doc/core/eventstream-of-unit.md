## `.flatMapTo`

Just a syntax sugar to skip `_ =>` in  `stream.flatMap(_ => ...)`

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.all._

val stream: EventStream[Unit] = ???
def makeAnotherStream: EventStream[String] = ???

val output: EventStream[String] = stream.flatMapTo(makeAnotherStream)
```
