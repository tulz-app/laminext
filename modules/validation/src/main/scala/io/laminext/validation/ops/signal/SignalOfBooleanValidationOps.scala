package io.laminext.validation.ops.signal

import io.laminext.validation.ValidatedValue
import io.laminext.validation.Validations
import com.raquo.airstream.signal.Signal

class SignalOfBooleanValidationOps(s: Signal[Boolean]) {

  def validatedIsTrue(message: String): Signal[ValidatedValue[Boolean]] = s.map(Validations.isTrue(message))

  def validatedIsFalse(message: String): Signal[ValidatedValue[Boolean]] = s.map(Validations.isFalse(message))

}
