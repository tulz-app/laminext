package io.laminext.ops.observable

import com.raquo.laminar.api.L._
import com.raquo.laminar.modifiers.Binder
import com.raquo.laminar.nodes.ReactiveElement

final class ObservableOps[A](underlying: Observable[A]) {

  @inline def bind(onNext: A => Unit): Binder[ReactiveElement.Base] = underlying --> onNext

  @inline def bindCollect(onNext: PartialFunction[A, Unit]): Binder[ReactiveElement.Base] =
    underlying --> { a =>
      if (onNext.isDefinedAt(a)) {
        onNext(a)
      }
    }

}
