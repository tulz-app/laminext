package io.laminext.validation
package ops.signal

import com.raquo.airstream.signal.Signal

class SignalOfStringValidationOps(s: Signal[String]) {

  def validated(validation: Validation[String]): Signal[ValidatedValue[String]] = s.map(validation)

}
