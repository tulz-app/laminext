package app.tulz.validation.ops.signal

import app.tulz.validation.ValidatedValue
import app.tulz.validation.Validation
import app.tulz.validation.Validations
import com.raquo.airstream.signal.Signal

class SignalOfStringValidationOps(s: Signal[String]) {

  def validated(validation: Validation[String]): Signal[ValidatedValue[String]] = s.map(validation)

  def validatedEmail(message: String): Signal[ValidatedValue[String]] = s.map(Validations.email(message))

  def validatedNonBlank(message: String): Signal[ValidatedValue[String]] = s.map(Validations.nonBlank(message))

  def validatedAlways: Signal[ValidatedValue[String]] = s.map(Validations.pass)

}
