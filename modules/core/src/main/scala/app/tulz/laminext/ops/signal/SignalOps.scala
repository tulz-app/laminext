package app.tulz.laminext.ops.signal

import com.raquo.laminar.api.L._
import app.tulz.tuplez.Composition
import app.tulz.tuplez.TupleComposition
import com.raquo.airstream.signal.Signal
import com.raquo.laminar.modifiers.Binder
import com.raquo.laminar.nodes.ReactiveElement

final class SignalOps[A](underlying: Signal[A]) {

  def combineWithC[AA >: A, B](otherSignal: Signal[B])(implicit compose: Composition[AA, B]): Signal[compose.Composed] = {
    underlying.combineWith(otherSignal).map2 { (a, b) =>
      TupleComposition.compose(a, b)
    }
  }

  @inline def bind(onNext: A => Unit): Binder[ReactiveElement.Base] = underlying --> onNext

  @inline def bindCollect(onNext: PartialFunction[A, Unit]): Binder[ReactiveElement.Base] =
    underlying --> { a =>
      if (onNext.isDefinedAt(a)) {
        onNext(a)
      }
    }

}
