## A styled component for file inputs

```scala
def fileInput[Err](
  validation: Validation[File, Err, File],
  noFileError: Err,
  styling: FileInputElement.Styling,
  inputMods: Modifier[ReactiveHtmlElement[org.scalajs.dom.html.Input]] = emptyMod,
  labelSelecting: Element = defaultLabelSelecting,
  labelReady: Seq[File] => Element = defaultLabelReady
): FileInputElement[Err, File]
```

```scala
def multiFileInput[Err](
  validation: Validation[File, Err, File],
  noFileError: Err,
  styling: FileInputElement.Styling,
  inputMods: Modifier[ReactiveHtmlElement[org.scalajs.dom.html.Input]] = emptyMod,
  labelSelecting: Element = defaultLabelSelecting,
  labelReady: Seq[File] => Element = defaultLabelReady
): FileInputElement[Either[Err, Seq[(File, Err)]], Seq[File]]
```

### Single-file input

First, you define a validation (from the `validation` module):

```scala
import org.scalajs.dom.raw.File
import io.laminext.syntax.validation._

val fileValidation: Validation[File, Seq[String], File] =
  V.file(Seq("must be a .png file"))(_.name.split('.').lastOption.map(_.toLowerCase).contains("png"))
```

Next, define the styling for you file input. 

It is defined by a `Signal[Status] => Mod[HtmlElement]` function, which is aliased as `FileInputElement.Styling`.

If you're using CSS classes to style your components, there's a helper function: `FileInputElement.Styling.classes`. 
It allows you to simply define a mapping between `FileInputElement.Status` and a `String` containing the CSS classes: 

```scala
val styling = FileInputElement.Styling.classes {
    case FileInputElement.Status.Selecting => "btn-md-outline-blue"
    case FileInputElement.Status.Ready     => "btn-md-outline-green"
    case FileInputElement.Status.Invalid   => "btn-md-outline-red"
}
```

Finally, define your file input button:

```scala
import com.raquo.laminar.api.L._
import io.laminext.ui._

val fileInputButton = fileInput(
  validation = fileValidation,
  noFileError = Seq("no file selected"),
  styling = styling
)
```

### Multi-file input

```scala
val multiFileInputButton = multiFileInput(
  validation = fileValidation,
  noFileError = Seq("no file selected"),
  styling = styling
)
```

### Customizing the underlying input element

You can provide a modifier for the underlying input element using the
`inputMods` parameter:

```scala
fileInput(
  // ...
  inputMods = Seq(
    accept := ".png .jpg",
    disabled <-- disabledSignal
  )
  // ...
)
```

### FileInputElement API

`fileInput` and `miltiFileInput` return an instance of `FileInputElement`.

`FileInputElement` extends the `ComponentBase` trait, which means it can be used directly as a `Modifier`.

`FileInputElement` for a single file input  has the following fields (`Err` comes from the validation you provide):
 * `value: Signal[Seq[File]]` – raw user input (selected files)
 * `validatedValue: Signal[ValidatedValue[Err, File]]` 
 * `validationError: Signal[Option[Err]]` 

For multi-file select:

* `value: Signal[Seq[File]]` – raw user input (selected files)
* `validatedValue: Signal[ValidatedValue[Either[Err, Seq[(File, Err)]], Seq[Files]]]`
* `validationError: Signal[Option[Either[Err, Seq[(File, Err)]]]]`

`Either[Err, Seq[(File, Err)]]` stands for the following:

* if there are no files selected, the error will be `Left(noFileError)` 
* if there are files that do not pass the validation, the error will be 
  `Right(Seq(file1 -> error, file2 -> error, ...))` (`file1`, `file2`, ... – invalid files)


See the [example](/ui/example-file-input)
