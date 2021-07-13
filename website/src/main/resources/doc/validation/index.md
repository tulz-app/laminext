```scala
libraryDependencies += "io.laminext" %%% "validation" % "{{laminextVersion}}"
```

```scala
import io.laminext.syntax.validation._
```

This module provides utilities to validate input field values.

## `.validated`
## `.validatedCheckbox`
## `.validatedFiles`

These are extension methods defined for inputs and text areas.

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.validation._

val validatedInput = input().validated(???)
val validatedTextArea = textArea().validated(???)
val validatedCheckbox = input(tpe := "checkbox").validatedCheckBox(???)
val validatedFiles = input(tpe := "file").validatedFiles(???)

// this is an alias for .validatedFiles for validations that return a single file
val validatedFile = input(tpe := "file").validatedFile(???)
```

`.validated*` methods accept a single parameter: `validation: Validation[In, Err, Out]`.

`Validation[A]` is defined as follows:

```scala
type ValidatedValue[Err, A]  = Either[Err, A]
type Validation[A, Err, Out] = A => ValidatedValue[Err, Out]
````

For text inputs and text areas, `In` and `Out` are both `String`.

For checkboxes, `In` and `Out` are both `Boolean`.

`Err` is `Seq[String]`.

For files, `In` is `Seq[File]`, `Out` is defined by the validation (can be `Seq[File]`, `File` or anything that the validation 
returns). `Err` is defined by the validation.

There are a few basic validations provided out of the box:

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.validation._

// e-mail validation
input().validated(V.email("please provide a valid e-mail"))

// non-blank validation
input().validated(V.nonBlank("required!"))

// no-op validation (always passes)
input().validated(V.pass)

// checkbox validations
input(tpe := "checkbox").validatedCheckBox(V.isTrue("must be checked!"))
input(tpe := "checkbox").validatedCheckBox(V.isFalse("must be un-checked!"))

// files validation
val filesValidation: Validation[Seq[File], Seq[ValidationError], Seq[File]] = files => ???
input(tpe := "file").validatedFiles(filesValidation)

// single file validation
val fileValidation: Validation[Seq[File], Seq[ValidationError], File] = files => ???
input(tpe := "file").validatedFile(fileValidation)

// custom validation
input().validated(V.custom("must be upper-case!")(string => string.toUpperCase == string))
```

## `.validatedValue`, `.validatationError`, `.resetError`

The `.validation` extension method returns a `ValidatedElement`, which has the following fields:

* `el` – is the original element (`input` or `textArea`); can be implicitly converted into a normal element so you can put the
  validated element into your component tree directly
* `validatedValue` – a signal with either a validation error, or a value that passed the validation
* `validationError` – a signal with an option that contains the validation error, if any. 
  `validationError`'s value is not immediately set to `Some(errors)`. Rather, it is set when the value is not valid, and the
element has lost the focus. Or if the element contained an invalid value when getting the focus, and its current value is 
invalid again (or vice versa).
* `resetError` – an observer which, when written to, reset the behavior of the `.validationError` signal back to its initial
state (as if the input has not been "touched" by the user yet).


## Combining validations

Validations can be combined using the `&&` combinator (fail fast – will only report the first 
validation error, see [cats](validation/cats) if you need `&` and `|`):

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.validation._

input().validated(
  V.nonBlank("must not be blank!") &&
    V.custom("must be upper-case!")(string => string.toUpperCase == string)
)
```

Check out the [example](/validation/example-validation) to see how it works.

## Validating event streams and signals

The `.validated` extension method is provided for signals and event streams:

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.validation._

val signal: Signal[String] = ???
val stream: EventStream[String] = ???

val validatedSignal: Signal[Either[Seq[String], String]] = signal.validated(V.email())
val validatedStream: EventStream[Either[Seq[String], String]] = stream.validated(V.email())
```

Or, you can just do `signal.map(V.email())`.

