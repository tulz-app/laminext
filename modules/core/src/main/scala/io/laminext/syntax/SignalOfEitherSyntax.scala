package io.laminext.syntax

import com.raquo.airstream.signal.Signal
import io.laminext.ops.signal.SignalOfEitherOps

trait SignalOfEitherSyntax {

  implicit def syntaxSignalOfEither[A, B](
    s: Signal[Either[A, B]]
  ): SignalOfEitherOps[A, B] = new SignalOfEitherOps[A, B](s)

}
