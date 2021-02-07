A styled component for file inputs.

First, you define a validation (from the `validation` module):

```scala
import org.scalajs.dom.raw.File
import io.laminext.syntax.validation._

val fileValidation: Validation[File, Seq[String], File] =
  V.file(Seq("must be a .png file"))(_.name.split('.').lastOption.map(_.toLowerCase).contains("png"))
```

Next, define a validated file input:

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.validation._

val fileInput = input(tpe := "file").validatedFile(Seq("no file"), fileValidation)
```

And finally, define your file input button:


```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.tailwind._

val fileInputButton = 
  button.btn.md.fill.build().fileInput(fileInput)
```

The `fileInputButton` will be responsible for rendering the file input as a button.

The `fileInput` will provide the `validatedValue` as a `Signal[ValidatedValue[File]]`,
and the `validationError`. 

See the [example](/tailwind/example-file-input)
