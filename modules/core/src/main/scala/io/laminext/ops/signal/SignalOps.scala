package io.laminext.ops.signal

import com.raquo.laminar.api.L._
import com.raquo.airstream.signal.Signal
import com.raquo.laminar.modifiers.Binder
import com.raquo.laminar.nodes.ReactiveElement

final class SignalOps[A](underlying: Signal[A]) {

  @inline def transitions: Signal[(Option[A], A)] = {
    var previous = Option.empty[A]
    underlying.map { underlyingNext =>
      val next = (previous, underlyingNext)
      previous = Some(underlyingNext)
      next
    }
  }

  @inline def valueIs(value: A): Signal[Boolean] =
    underlying.map(_ == value)

  @inline def bind(onNext: A => Unit): Binder[ReactiveElement.Base] = underlying --> onNext

  @inline def bindCollect(onNext: PartialFunction[A, Unit]): Binder[ReactiveElement.Base] =
    underlying --> { a =>
      if (onNext.isDefinedAt(a)) {
        onNext(a)
      }
    }

  def previousAndCurrent: Signal[(Option[A], Option[A])] =
    underlying.foldLeft(initial => (Option.empty[A], Some(initial))) { (previous, current) =>
      (previous._2, Some(current))
    }

}
