## `.changes`


* `.changes: EventStream[Event]`
* `.changes(changeStreamTransform: EventStream[Event] => EventStream[Event]): EventStream[Event]`

Returns an event stream that emits whenever the element's value changes (typing, copy-paste, etc.).

More precisely, this event stream emits when one of the following events occur:
* `onChange`
* `onInput`
* `onBlur`
* `onPaste`
* `onCut`

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

val inputElement = input()

val changes: EventStream[Unit] = inputElement.changes
```

There is also an overloaded version that accepts a `changeStreamTransform: EventStream[Event] => EventStream[Event]` function
that can be used to modify the stream of `onChange` + `onInput` events. The `onBlur`, `onPaste` and `onCut` events
are not affected by this transformation.

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

val inputElement = input()

val changes: EventStream[Unit] = inputElement.changes(_.debounce(100))
```


## `.value`

* `.value: Signal[String]`
* `.value(changeStreamTransform: EventStream[Event] => EventStream[Event]): Signal[String]`

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

val inputElement = input()
val textElement = textArea()

val inputValue: Signal[String] = inputElement.value
val textValue:  Signal[String] = textElement.value
val textValueDebounced:  Signal[String] = textElement.value(_.debounce(100))
```

## `.checked`

* `.checked: Signal[Boolean]`
* `.checked(changeStreamTransform: EventStream[Event] => EventStream[Event]): Signal[Boolean]`

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

val checkboxElement = input(`type` := "checkbox")

val checkValue: Signal[Boolean] = checkboxElement.checked
val checkValueDebounced: Signal[Boolean] = checkboxElement.checked(_.debounce(100))
```

## `.files`

* `.files: Signal[Seq[File]]`
* `.files(changeStreamTransform: EventStream[Event] => EventStream[Event]): Signal[Seq[File]]`

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._

val checkboxElement = input(`type` := "file")

val filesValue: Signal[Seq[File]] = checkboxElement.files
val checkValueDebounced: Signal[Seq[File]] = checkboxElement.files(_.debounce(100))
```

See [example](/core/example-input-values).
