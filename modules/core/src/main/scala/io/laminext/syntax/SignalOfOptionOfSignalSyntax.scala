package io.laminext.syntax

import com.raquo.airstream.signal.Signal
import io.laminext.ops.signal.SignalOfOptionOfSignalOps

trait SignalOfOptionOfSignalSyntax {

  implicit def syntaxSignalOfOptionOfSignal[A](
    s: Signal[Option[Signal[A]]]
  ): SignalOfOptionOfSignalOps[A] = new SignalOfOptionOfSignalOps[A](s)

}
