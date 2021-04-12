package io.laminext.validation
package ops.element

import com.raquo.laminar.api.L._
import io.laminext.syntax.core._
import io.laminext.validation.components.ValidatedElement
import io.laminext.validation.ops.ValidationOps
import org.scalajs.dom.raw.File
import org.scalajs.dom.html

final class InputValidationOps(el: Input) {

  @inline def validated[Err](
    validation: Validation[String, Err, String],
  ): ValidatedElement[html.Input, String, Err, String] = {
    val value = el.value
    ValidatedElement(el, value, validation)
  }

  @inline def validatedCheckBox[Err](
    validation: Validation[Boolean, Err, Boolean],
  ): ValidatedElement[html.Input, Boolean, Err, Boolean] = {
    val value = el.checked
    ValidatedElement(el, value, validation)
  }

  private def firstFileValidation[Err](noFileError: Err): Validation[Seq[File], Err, File] =
    files =>
      if (files.isEmpty) {
        Left(noFileError)
      } else {
        Right(files.head)
      }

  @inline def validatedFile[Err](
    noFileError: Err,
    validation: Validation[File, Err, File],
  ): ValidatedElement[html.Input, Seq[File], Err, File] =
    ValidatedElement(
      el,
      el.files,
      new ValidationOps(firstFileValidation(noFileError)).flatMap(validation)
    )

  @inline def validatedFiles[Err, Out](
    validation: Validation[Seq[File], Err, Out],
  ): ValidatedElement[html.Input, Seq[File], Err, Out] = ValidatedElement(el, el.files, validation)

}
