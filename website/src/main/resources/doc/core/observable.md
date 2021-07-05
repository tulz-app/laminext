## `.bind` 
## `.bindCollect`

`.bind` is an alias for `-->(onNext: A => Unit)`.

`.bindCollect` is the same, but checks if the provided partial function is defined at the value.

```scala
import org.scalajs.dom
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

val obs: Observable[String] = ???

div(
  obs.bind { string => 
    dom.console.log(string)  
  },
  obs.bindCollect {
    case s if s.length < 5 =>
      dom.console.log("it's shorter than 5!")
  }  
)
```

## `.addSwitchingObserver`
## `.addOptionalSwitchingObserver`

If you have a `Source[Observer[A]]` or a `Source[Option[Observer[A]]]`, you can dynamically subscribe the observers
emitted by the source to an observable.

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

val $observer: Signal[Option.empty[Observer[String]]] = ???
val messages: EventStream[String]  = ???

div(
  messages.addOptionalSwitchingObserver($observer)
)

```

The current (for signals) or the last emitted (for event stream) observer will get subscribed to
observable, the previous observers will get unsubscribed.

See [example](/core/example-add-switching-observer).
