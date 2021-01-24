package io.laminext.syntax

import com.raquo.laminar.api.L._
import io.laminext.core.ops.signal.SignalOfTuple2Ops

trait SignalOfTuple2Syntax {

  implicit def syntaxSignalOfOption[A, B](
    s: Signal[(A, B)]
  ): SignalOfTuple2Ops[A, B] = new SignalOfTuple2Ops[A, B](s)

}
