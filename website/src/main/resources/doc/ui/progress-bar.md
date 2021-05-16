## A simple progress bar

```scala
def progressBar(
  progress: Signal[Double],
  styling: ProgressBarElement.Styling
): ProgressBarElement
```

Styling is defined by two modifiers: one for the wrapping element, and one
for the inner element that grows according to the progress signal.

```scala
val styling = ProgressBarElement.Styling(
  wrap = cls("w-full rounded bg-green-200"),
  progress = cls("bg-green-500 h-2 rounded")
)
```

Progress is represented by a `Signal[Double]`:

```scala
val myProgress = EventStream.periodic(300).map(n => ((n * 3) % 100).toDouble).toSignal(0.0) 

```

Building a progress bar:

```scala
progressBar(
  progress = myProgress,
  styling = styling
)
```

See the [example](/ui/example-progress-bar)
