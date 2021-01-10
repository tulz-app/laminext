package io.laminext.syntax

import com.raquo.laminar.api.L._
import io.laminext.ops.signal.SignalOfOptionOps

trait SignalOfOptionSyntax {

  implicit def syntaxSignalOfOption[A](
    s: Signal[Option[A]]
  ): SignalOfOptionOps[A] = new SignalOfOptionOps[A](s)

}
