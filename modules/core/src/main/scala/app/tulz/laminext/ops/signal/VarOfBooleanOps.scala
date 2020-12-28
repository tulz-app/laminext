package app.tulz.laminext.ops.signal

import com.raquo.airstream.signal.Var

final class VarOfBooleanOps(underlying: Var[Boolean]) {

  @inline def toggle(): Unit = underlying.update(!_)

}
