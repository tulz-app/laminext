package io.laminext.syntax

import com.raquo.airstream.signal.Signal
import io.laminext.ops.signal.SignalOfBooleanOps

trait SignalOfBooleanSyntax {

  implicit def syntaxSignalOfBoolean(s: Signal[Boolean]): SignalOfBooleanOps =
    new SignalOfBooleanOps(s)

}
