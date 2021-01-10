package io.laminext.ops.signal

import com.raquo.airstream.core.Observer
import com.raquo.airstream.signal.Var

final class VarOfBooleanOps(underlying: Var[Boolean]) {

  @inline def toggle(): Unit                = underlying.update(!_)
  @inline def toggleObserver: Observer[Any] = Observer.apply(_ => underlying.update(!_))

}
