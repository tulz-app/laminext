package io.laminext.core
package ops.signal

import com.raquo.laminar.api.L._

final class VarOfBooleanOps(underlying: Var[Boolean]) {

  @inline def toggle(): Unit                = underlying.update(!_)
  @inline def toggleObserver: Observer[Any] = underlying.updater((prev, _) => !prev)

}
