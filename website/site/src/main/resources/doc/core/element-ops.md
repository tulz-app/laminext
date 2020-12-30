```scala
val inputElement = input()
val checkboxElement = input(`type` := "checkbox")
val textElement = textArea()


val inputValue: Signal[String] = inputElement.valueSignal
val checkValue: Signal[String] = checkboxElement.checkedSignal
val textValue: Signal[String] = textElement.valueSignal

```

See [example](/core/input-values).
