package io.laminext.validation
package ops.stream

import com.raquo.airstream.eventstream.EventStream

class StreamOfBooleanValidationOps(s: EventStream[Boolean]) {

  def validatedIsTrue(validation: Validation[Boolean]): EventStream[ValidatedValue[Boolean]] = s.map(validation)

}
