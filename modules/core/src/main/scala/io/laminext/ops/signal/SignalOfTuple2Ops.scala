package io.laminext.ops.signal

import com.raquo.airstream.signal.Signal
import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveElement
import io.laminext.ConditionalChildInserter

final class SignalOfTuple2Ops[A, B](underlying: Signal[(A, B)]) {

  def unzip: (Signal[A], Signal[B]) = (underlying.map(_._1), underlying.map(_._2))

}
