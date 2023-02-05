package io.laminext.site.examples.validation.ex_validation_cats

import com.yurique.embedded.FileAsString
import io.laminext.site.examples.CodeExample

object ValidationCatsExample
    extends CodeExample(
      id = "example-validation-cats",
      title = "Validation (cats)",
      description = FileAsString("description.md")
    )(() => {
      import com.raquo.laminar.api.L._
      import io.laminext.syntax.core._
      import io.laminext.syntax.validation.cats._

      /* <focus> */
      val validatedInput1 = input(
        tpe         := "email",
        placeholder := "enter an e-mail address"
      ).validated(V.email("Please enter a valid e-mail address."))
      /* </focus> */

      /* <focus> */
      val validatedInput2 = input(
        tpe         := "text",
        placeholder := "enter something BIG"
      ).validated(
        V.nonBlank("Must not be blank!") &
          V.custom("Must be upper-case!")(string => string.toUpperCase == string)
      )
      /* </focus> */

      /* <focus> */
      val validatedInput3 = input(
        tpe         := "text",
        placeholder := "enter something else"
      ).validated(
        V.custom("Must be longer than 5 characters!")(_.length > 5) &
          (V.custom("Must be upper-case!")(string => string.toUpperCase == string) |
            V.custom("Must be lower-case!")(string => string.toLowerCase == string))
      )
      /* </focus> */

      div(
        cls := "p-4 flex flex-col space-y-4",
        div(validatedInput1),
        div(
          /* <focus> */
          child.maybe <-- validatedInput1.validationError.optionMap(errors =>
            span(
              cls := "text-red-700 text-sm",
              errors.map(error => div(error))
            )
          )
          /* </focus> */
        ),
        div(validatedInput2),
        div(
          /* <focus> */
          child.maybe <-- validatedInput2.validationError.optionMap(errors =>
            span(
              cls := "text-red-700 text-sm",
              errors.map(error => div(error))
            )
          )
          /* </focus> */
        ),
        div(validatedInput3),
        div(
          /* <focus> */
          child.maybe <-- validatedInput3.validationError.optionMap(errors =>
            span(
              cls := "text-red-700 text-sm",
              errors.map(error => div(error))
            )
          )
          /* </focus> */
        ),
        div(
          cls := "flex items-center space-x-4",
          div(
            button(
              "Reset values",
              cls := "btn-sm-outline-blue",
              onClick --> { _ =>
                validatedInput1.ref.value = ""
                validatedInput2.ref.value = ""
                validatedInput3.ref.value = ""
              }
            )
          ),
          div(
            button(
              "Reset errors",
              cls := "btn-sm-outline-blue",
              /* <focus> */
              onClick.mapToUnit --> validatedInput1.resetError,
              onClick.mapToUnit --> validatedInput2.resetError,
              onClick.mapToUnit --> validatedInput3.resetError
              /* </focus> */
            )
          )
        ),
        div(
          cls := "flex space-x-4 items-center text-xs",
          code("validatedInput1.el.value:"),
          code(
            cls := "text-blue-700 font-medium",
            child.text <-- validatedInput1.value
          )
        ),
        div(
          cls := "flex space-x-4 items-center text-xs",
          code("validatedInput1.validatedValue:"),
          code(
            cls := "text-blue-700 font-medium",
            /* <focus> */
            child.text <-- validatedInput1.validatedValue.map(_.toString)
            /* </focus> */
          )
        ),
        div(
          cls := "flex space-x-4 items-center text-xs",
          code("validatedInput2.el.value:"),
          code(
            cls := "text-blue-700 font-medium",
            child.text <-- validatedInput2.value
          )
        ),
        div(
          cls := "flex space-x-4 items-center text-xs",
          code("validatedInput2.validatedValue:"),
          code(
            cls := "text-blue-700 font-medium",
            /* <focus> */
            child.text <-- validatedInput2.validatedValue.map(_.toString)
            /* </focus> */
          )
        ),
        div(
          cls := "flex space-x-4 items-center text-xs",
          code("validatedInput3.el.value:"),
          code(
            cls := "text-blue-700 font-medium",
            child.text <-- validatedInput3.value
          )
        ),
        div(
          cls := "flex space-x-4 items-center text-xs",
          code("validatedInput3.validatedValue:"),
          code(
            cls := "text-blue-700 font-medium",
            /* <focus> */
            child.text <-- validatedInput3.validatedValue.map(_.toString)
            /* </focus> */
          )
        )
      )
    })
