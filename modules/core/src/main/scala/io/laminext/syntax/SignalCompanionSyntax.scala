package io.laminext.syntax

import com.raquo.airstream.signal.Signal
import io.laminext.ops.signal.SignalCompanionOps

trait SignalCompanionSyntax {

  implicit def syntaxSignalCompanion(s: Signal.type): SignalCompanionOps.type = SignalCompanionOps

}
