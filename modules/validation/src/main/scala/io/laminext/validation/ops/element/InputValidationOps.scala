package io.laminext.validation
package ops.element

import com.raquo.laminar.api.L._
import io.laminext.syntax.all._
import org.scalajs.dom.html

final class InputValidationOps(el: Input) {

  @inline def validated(
    validation: Validation[String],
  ): ValidatedElement[html.Input, String] = {
    val value = el.value
    ValidatedElement(el, value, validation)
  }

  @inline def validatedCheckBox(
    validation: Validation[Boolean],
  ): ValidatedElement[html.Input, Boolean] = {
    val value = el.checked
    ValidatedElement(el, value, validation)
  }

}
