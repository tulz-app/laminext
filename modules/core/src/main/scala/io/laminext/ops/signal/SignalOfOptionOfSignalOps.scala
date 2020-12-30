package io.laminext.ops.signal

import com.raquo.airstream.signal.Signal
import com.raquo.laminar.api.L._

final class SignalOfOptionOfSignalOps[A](s: Signal[Option[Signal[A]]]) {

  def shiftOption: Signal[Option[A]] = {
    s.flatMap {
      case Some(subSignal) => subSignal.map(a => Option(a))
      case None            => Val(Option.empty[A])
    }
  }

}
