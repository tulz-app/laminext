package io.laminext.validation
package ops

import com.raquo.laminar.api.L._
import io.laminext.validation.ValidatedValue
import io.laminext.validation.Validation

final class SignalValidationOps[A](s: Signal[A]) {

  def validated[Err, Out](validation: Validation[A, Err, Out]): Signal[ValidatedValue[Err, Out]] = s.map(validation)

}
