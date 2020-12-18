package app.tulz.validation.ops.stream

import app.tulz.validation.ValidatedValue
import app.tulz.validation.Validations
import com.raquo.airstream.eventstream.EventStream

class StreamOfBooleanValidationOps(s: EventStream[Boolean]) {

  def validatedIsTrue(message: String): EventStream[ValidatedValue[Boolean]] =
    s.map(Validations.isTrue(message))

}
