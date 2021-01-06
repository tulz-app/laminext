package io.laminext.validation
package ops.signal

import com.raquo.airstream.signal.Signal

class SignalOfBooleanValidationOps(s: Signal[Boolean]) {

  def validated(validation: Validation[Boolean]): Signal[ValidatedValue[Boolean]] = s.map(validation)

}
