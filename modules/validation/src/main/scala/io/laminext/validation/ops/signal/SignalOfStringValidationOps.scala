package io.laminext.validation.ops.signal

import io.laminext.validation.ValidatedValue
import io.laminext.validation.Validation
import io.laminext.validation.Validations
import com.raquo.airstream.signal.Signal

class SignalOfStringValidationOps(s: Signal[String]) {

  def validated(validation: Validation[String]): Signal[ValidatedValue[String]] = s.map(validation)

  def validatedEmail(message: String): Signal[ValidatedValue[String]] = s.map(Validations.email(message))

  def validatedNonBlank(message: String): Signal[ValidatedValue[String]] = s.map(Validations.nonBlank(message))

  def validatedAlways: Signal[ValidatedValue[String]] = s.map(Validations.pass)

}
