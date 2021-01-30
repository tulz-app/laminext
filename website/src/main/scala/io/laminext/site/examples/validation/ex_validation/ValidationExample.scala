package io.laminext.site.examples.validation.ex_validation

import com.yurique.embedded.FileAsString
import io.laminext.site.examples.CodeExample

object ValidationExample
    extends CodeExample(
      id = "example-validation",
      title = "Validation",
      description = FileAsString("description.md")
    )(() => {
      import com.raquo.laminar.api.L._
      import io.laminext.syntax.core._
      import io.laminext.syntax.cats._

      /* <focus> */
      val validatedInput1 = input(
        tpe := "email",
        cls := "shadow-sm focus:ring-blue-500 focus:border-blue-500 block w-full sm:text-sm border-blue-300 rounded-md bg-blue-50 text-blue-700 placeholder-blue-400 font-mono",
        placeholder := "enter an e-mail address"
      ).validated(V.email("Please enter a valid e-mail address."))
      /* </focus> */

      /* <focus> */
      val validatedInput2 = input(
        tpe := "text",
        cls := "shadow-sm focus:ring-blue-500 focus:border-blue-500 block w-full sm:text-sm border-blue-300 rounded-md bg-blue-50 text-blue-700 placeholder-blue-400 font-mono",
        placeholder := "enter something BIG"
      ).validated(
        V.all(
          V.nonBlank("Must not be blank!"),
          V.custom("Must be upper-case!")(string => string.toUpperCase == string)
        )
      )
      /* </focus> */

      /* <focus> */
      val validatedInput3 = input(
        tpe := "text",
        cls := "shadow-sm focus:ring-blue-500 focus:border-blue-500 block w-full sm:text-sm border-blue-300 rounded-md bg-blue-50 text-blue-700 placeholder-blue-400 font-mono",
        placeholder := "enter something else"
      ).validated(
        V.nonBlank("Must not be blank!") &&
          (V.custom("Must be upper-case!")(string => string.toUpperCase == string) ||
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
          cls := "flex space-x-4 items-center",
          code("validatedInput1.el.value:"),
          code(
            cls := "text-blue-700 font-medium",
            child.text <-- validatedInput1.el.value
          )
        ),
        div(
          cls := "flex space-x-4 items-center",
          code("validatedInput1.validatedValue:"),
          code(
            cls := "text-blue-700 font-medium",
            /* <focus> */
            child.text <-- validatedInput1.validatedValue.map(_.toString)
            /* </focus> */
          )
        ),
        div(
          cls := "flex space-x-4 items-center",
          code("validatedInput2.el.value:"),
          code(
            cls := "text-blue-700 font-medium",
            child.text <-- validatedInput2.el.value
          )
        ),
        div(
          cls := "flex space-x-4 items-center",
          code("validatedInput2.validatedValue:"),
          code(
            cls := "text-blue-700 font-medium",
            /* <focus> */
            child.text <-- validatedInput2.validatedValue.map(_.toString)
            /* </focus> */
          )
        ),
        div(
          cls := "flex space-x-4 items-center",
          code("validatedInput3.el.value:"),
          code(
            cls := "text-blue-700 font-medium",
            child.text <-- validatedInput3.el.value
          )
        ),
        div(
          cls := "flex space-x-4 items-center",
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
