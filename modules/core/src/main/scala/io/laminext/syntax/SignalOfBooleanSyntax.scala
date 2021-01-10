package io.laminext.syntax

import com.raquo.laminar.api.L._
import io.laminext.ops.signal.SignalOfBooleanOps

trait SignalOfBooleanSyntax {

  implicit def syntaxSignalOfBoolean(s: Signal[Boolean]): SignalOfBooleanOps =
    new SignalOfBooleanOps(s)

}
