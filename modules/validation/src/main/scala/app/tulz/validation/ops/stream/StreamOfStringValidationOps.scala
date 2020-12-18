package app.tulz.validation.ops.stream

import app.tulz.validation.ValidatedValue
import app.tulz.validation.Validation
import app.tulz.validation.Validations
import com.raquo.airstream.eventstream.EventStream

class StreamOfStringValidationOps(s: EventStream[String]) {

  def validated(validation: Validation[String]): EventStream[ValidatedValue[String]] = s.map(validation)

  def validatedEmail(message: String): EventStream[ValidatedValue[String]] = s.map(Validations.email(message))

  def validatedNonBlank(message: String): EventStream[ValidatedValue[String]] = s.map(Validations.email(message))

  def validatedAlways: EventStream[ValidatedValue[String]] = s.map(Validations.pass)

}
