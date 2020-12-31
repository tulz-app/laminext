```scala
val inputElement    = input()
val checkboxElement = input(`type` := "checkbox")
val textElement     = textArea()

val inputValue: Signal[String]  = inputElement.valueSignal
val checkValue: Signal[Boolean] = checkboxElement.checkedSignal
val textValue:  Signal[String]  = textElement.valueSignal

```

See [example](/core/example-input-values).
