package io.laminext.validation
package ops.signal

import com.raquo.laminar.api.L._

final class SignalValidationOps[A](s: Signal[A]) {

  def validated(validation: Validation[A]): Signal[ValidatedValue[A]] = s.map(validation)

}
