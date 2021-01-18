package io.laminext.validation
package ops.stream

import com.raquo.airstream.core.EventStream

class EventStreamValidationOps[A](s: EventStream[A]) {

  def validated(validation: Validation[A]): EventStream[ValidatedValue[A]] = s.map(validation)

}
