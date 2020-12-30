package io.laminext.syntax

import com.raquo.airstream.signal.Signal
import io.laminext.ops.option.OptionOfSignalOps

trait OptionOfSignalSyntax {

  implicit def syntaxOptionOfSignal[A](
    o: Option[Signal[A]]
  ): OptionOfSignalOps[A] = new OptionOfSignalOps[A](o)

}
