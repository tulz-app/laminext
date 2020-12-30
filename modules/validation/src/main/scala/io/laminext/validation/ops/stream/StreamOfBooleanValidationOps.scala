package io.laminext.validation.ops.stream

import io.laminext.validation.ValidatedValue
import io.laminext.validation.Validations
import com.raquo.airstream.eventstream.EventStream

class StreamOfBooleanValidationOps(s: EventStream[Boolean]) {

  def validatedIsTrue(message: String): EventStream[ValidatedValue[Boolean]] = s.map(Validations.isTrue(message))

  def validatedIsFalse(message: String): EventStream[ValidatedValue[Boolean]] = s.map(Validations.isFalse(message))

}
