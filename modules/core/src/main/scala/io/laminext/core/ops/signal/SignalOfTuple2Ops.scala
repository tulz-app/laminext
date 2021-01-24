package io.laminext.core
package ops.signal

import com.raquo.laminar.api.L._

final class SignalOfTuple2Ops[A, B](underlying: Signal[(A, B)]) {

  def unzip: (Signal[A], Signal[B]) = (underlying.map(_._1), underlying.map(_._2))

}
