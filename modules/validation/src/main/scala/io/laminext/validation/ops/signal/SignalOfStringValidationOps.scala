package io.laminext.validation
package ops.signal

import com.raquo.laminar.api.L._

class SignalOfStringValidationOps(s: Signal[String]) {

  def validated(validation: Validation[String]): Signal[ValidatedValue[String]] = s.map(validation)

}
