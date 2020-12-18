package app.tulz.validation.ops.signal

import app.tulz.validation.ValidatedValue
import app.tulz.validation.Validations
import com.raquo.airstream.signal.Signal

class SignalOfBooleanValidationOps(s: Signal[Boolean]) {

  def validatedIsTrue(message: String): Signal[ValidatedValue[Boolean]] = s.map(Validations.isTrue(message))

}
