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

## `nodeSeq` and `nodeSequence`

Combine a sequence of modifiers into a `Modifier`.

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

val flag: Boolean = ???

div(
  if (flag) {
    nodeSeq(
      span("a"),
      span("b")
    )
  } else {
    emptyMod
  }
)
```

## `unsafeAppendRawChild`

Use when you know what you're doing.

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._
import org.scalajs.dom.raw

val rawNode: raw.Node = ???

div(
  unsafeAppendRawChild(rawNode)
)
```

## Tee observer

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

def component(observer: Observer[String]): Element = ???

val observer1: Observer[String] = ???
val observer2: Observer[String] = ???

div(
  component(
    tee(observer1, observer2)
  )
)
```

## `buildStream`

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

val stream: EventStream[String] = buildStream[String] { b =>
  b.onNext("a")
  b.onNext("b") 
}
```

## `createTrigger`

Just a thin wrapper around the `EventStream.withCallback`. Makes the intent more obvious.

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

val (triggerStream, triggerFunc) = createTrigger()
// triggerStream: EventStream[Unit]
// triggerFunc: () => Unit – calling this function makes the triggerStream emit a value 
```

## `storedBoolean` and `storedString`

Creates "var"-like objects that store their values in the local storage.

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

val myStoredString = storedString("my-id", initial = "")

myStoredString.signal // Signal[String]
myStoredString.set("new value")
myStoredString.update(oldValue => oldValue.toUpperCase)

myStoredString.setObserver // Observer[String]
myStoredString.updateObserver // Observer[String => String]

// storedBoolean also has .toggleObserver and .toggle()
```

## `after`

A `setTimeout` wrapper.

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._
import scala.concurrent.duration._
import org.scalajs.dom

div(
  after(5.seconds) {
    dom.console.log("after 5 seconds")    
  }
)
```

## `resizeObserver`

```scala
import com.raquo.laminar.api.L._
import io.laminext.domext.ResizeObserverEntry
import io.laminext.syntax.core._
import org.scalajs.dom

div(
  resizeObserver --> { entry: ResizeObserverEntry => 
    dom.console.log("borderBoxSize", entry.borderBoxSize)    
    dom.console.log("contentBoxSize", entry.contentBoxSize)    
    dom.console.log("contentRect", entry.contentRect)    
    dom.console.log("target", entry.target)    
  }
)

```

### `futureChild` and `futureChildren`

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._
import scala.concurrent.Future

div(
  futureChild <-- Future.successful(div("a")),  
  futureChildren <-- Future.successful(Seq(div("a"), div("b"))),  
)
```

## `unsafeInnerHtml`

Use when you know what you're doing.

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

val signal: Signal[String] = ???

div(
  div(
    unsafeInnerHtml := "<div>a</div>",
  ),
  div(
    unsafeInnerHtml <-- signal
  )
  
)
```
