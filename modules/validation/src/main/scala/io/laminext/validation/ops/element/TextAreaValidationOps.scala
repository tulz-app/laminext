package io.laminext.validation
package ops.element

import com.raquo.laminar.api.L._
import io.laminext.syntax.all._
import org.scalajs.dom.html

final class TextAreaValidationOps(el: TextArea) {

  @inline def validated(
    validation: Validation[String],
  ): ValidatedElement[html.TextArea, String] = {
    val value = el.value
    ValidatedElement.apply(el, value, validation)
  }

}
