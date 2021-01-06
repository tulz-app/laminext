package io.laminext.validation
package ops.stream

import io.laminext.validation.ValidatedValue
import io.laminext.validation.Validation
import io.laminext.validation.Validations
import com.raquo.airstream.eventstream.EventStream

class StreamOfStringValidationOps(s: EventStream[String]) {

  def validated(validation: Validation[String]): EventStream[ValidatedValue[String]] = s.map(validation)

}
