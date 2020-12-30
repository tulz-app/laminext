package io.laminext.syntax

import com.raquo.airstream.signal.Signal
import io.laminext.ops.signal.SignalOps

trait SignalSyntax {

  implicit def syntaxSignal[A](s: Signal[A]): SignalOps[A] = new SignalOps[A](s)

}
