package io.laminext.validation
package ops.element

import com.raquo.laminar.api.L._
import io.laminext.syntax.core._
import org.scalajs.dom.html

final class TextAreaValidationOps(el: TextArea) {

  @inline def validated[Err](
    validation: Validation[String, Err, String],
  ): ValidatedElement[html.TextArea, String, Err, String] = {
    val value = el.value
    ValidatedElement.apply(el, value, validation)
  }

}
