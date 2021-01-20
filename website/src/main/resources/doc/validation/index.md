```scala
libraryDependencies += "io.laminext" %%% "validation" % "{{laminextVersion}}"
```

```scala
import io.laminext.validation.syntax._
```

The `validation` module provides utilities to validate the input field values.

## `.validated`

This is an extension method defined for inputs and text areas.

```scala
import com.raquo.laminar.api.L._
import io.laminext.validation.syntax._

val validatedInput = input().validated(???)
val validatedTextArea = textArea().validated(???)
val validatedCheckbox = input(tpe := "checkbox").validatedCheckBox(???)
```

`.validated` accepts a single parameter: `validation: Validation[String]`.

`Validation[A]` is defined as follows:

```scala
type ValidatedValue[T] = Either[ValidationError, T]
type ValidationError   = Seq[String]
type Validation[T]     = T => ValidatedValue[T]
````

There is a few basic validations provided out of the box:

```scala
import com.raquo.laminar.api.L._
import io.laminext.validation.syntax._

// e-mail validation
input().validated(V.email("please provide a valid e-mail"))

// non-blank validation
input().validated(V.nonBlank("required!"))

// no-op validation (always passes)
input().validated(V.pass)

// checkbox validations
input(tpe := "checkbox").validatedCheckBox(V.isTrue("must be checked!"))
input(tpe := "checkbox").validatedCheckBox(V.isFalse("must be UNchecked!"))

// custom validation
input().validated(V.custom("must be upper-case!")(string => string.toUpperCase == string))

```

## `.validatedValue`

The `.validation` extension method returns a `ValidatedElement`:

```scala
import cats.data.NonEmptyChain
import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

class ValidatedElement[+Ref <: dom.html.Element, A](
  val el: ReactiveHtmlElement[Ref],
  val validatedValue: Signal[Either[NonEmptyChain[String], A]],
  val validationError: Signal[Option[NonEmptyChain[String]]]
)
```

* `el` is the original element (`input()`, `textArea`); can be implicitly converted into a normal element so you can put the 
  validated element into your component tree directly
* `validatedValue` is a signal with either a validation error (a non-empty chain of errors), or a value that passed the validations
* `validationError` is a signal with an option that contains the validation error, if any

`validationError`'s value is not immediately set to `Some(errors)`. Rather, it is set when the value is not valid, and the
element has lost the focus. Or if the element contained an invalid value when getting the focus, and its current value is 
invalid again.

## Combining validations

Validations can be combined using the `V.all` function:

```scala
import com.raquo.laminar.api.L._
import io.laminext.validation.syntax._

input().validated(V.all(
  V.nonBlank("must not be blank!"),
  V.custom("must be upper-case!")(string => string.toUpperCase == string)
))
```

Or using the `&&` and `||` operators:

```scala
import com.raquo.laminar.api.L._
import io.laminext.validation.syntax._

input().validated(
  V.nonBlank("Must not be blank!") &&
    (V.custom[String]("Must be upper-case!")(string => string.toUpperCase == string) ||
      V.custom[String]("Must be lower-case!")(string => string.toLowerCase == string))
)
```

Check out the [example](/validation/example-validation) to see how it works.

## Validating event streams and signals

The `.validated` extension method is provided for signals and event streams:

```scala
import com.raquo.laminar.api.L._
import io.laminext.validation.syntax._

val signal: Signal[String] = ???
val stream: EventStream[String] = ???

val validatedSignal: Signal[Either[Seq[String], String]] = signal.validated(V.email())
val validatedStream: EventStream[Either[Seq[String], String]] = stream.validated(V.email())
```

Or, you can just do `signal.map(V.email())`.

