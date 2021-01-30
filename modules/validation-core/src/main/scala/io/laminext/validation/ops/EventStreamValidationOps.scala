package io.laminext.validation
package ops

import com.raquo.laminar.api.L._
import io.laminext.validation.ValidatedValue
import io.laminext.validation.Validation

class EventStreamValidationOps[A](s: EventStream[A]) {

  def validated[Err, Out](validation: Validation[A, Err, Out]): EventStream[ValidatedValue[Err, Out]] = s.map(validation)

}
