## `.changes`

Returns an event stream that emits whenever the element's value changes (typing, copy-paste, etc.)

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.all._

val inputElement = input()

val changes: EventStream[Unit] = inputElement.changes
```

## `.value`

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.all._

val inputElement = input()
val textElement = textArea()

val inputValue: Signal[String] = inputElement.value
val textValue:  Signal[String] = textElement.value
```

## `.checked`

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.all._

val checkboxElement = input(`type` := "checkbox")

val checkValue: Signal[Boolean] = checkboxElement.checked
```

See [example](/core/example-input-values).
