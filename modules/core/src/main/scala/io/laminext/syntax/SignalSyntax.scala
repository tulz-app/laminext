package io.laminext.syntax

import com.raquo.laminar.api.L._
import io.laminext.ops.signal.SignalOps

trait SignalSyntax {

  implicit def syntaxSignal[A](s: Signal[A]): SignalOps[A] = new SignalOps[A](s)

}
