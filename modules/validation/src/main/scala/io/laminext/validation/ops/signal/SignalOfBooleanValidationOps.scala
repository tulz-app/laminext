package io.laminext.validation
package ops.signal

import com.raquo.laminar.api.L._

class SignalOfBooleanValidationOps(s: Signal[Boolean]) {

  def validated(validation: Validation[Boolean]): Signal[ValidatedValue[Boolean]] = s.map(validation)

}
