package io.laminext.syntax

import com.raquo.laminar.api.L._
import io.laminext.ops.signal.SignalOfOptionOfSignalOps

trait SignalOfOptionOfSignalSyntax {

  implicit def syntaxSignalOfOptionOfSignal[A](
    s: Signal[Option[Signal[A]]]
  ): SignalOfOptionOfSignalOps[A] = new SignalOfOptionOfSignalOps[A](s)

}
