package io.laminext.core

import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveElement
import io.laminext.core.binders.BinderWithStartStop
import io.laminext.core.binders.SetTimeoutBinder

import scala.concurrent.duration.FiniteDuration

class SetTimeoutBinders[V](
  value: V,
  timeout: FiniteDuration
) {

  @inline def -->[El <: ReactiveElement.Base](observer: Sink[V]): BinderWithStartStop[El] = {
    new SetTimeoutBinder(value, timeout, t => observer.toObserver.onNext(t))
  }

  @inline def -->[El <: ReactiveElement.Base](onNext: V => Unit): BinderWithStartStop[El] = {
    new SetTimeoutBinder(value, timeout, onNext)
  }

}

object SetTimeoutBinders {

  implicit class UnitTimeoutBindersOps(val binders: SetTimeoutBinders[Unit]) extends AnyVal {

    @inline def apply[El <: ReactiveElement.Base](onNext: => Unit): BinderWithStartStop[El] = binders --> { _ => onNext }

  }

  implicit class TimeoutBindersOps[V](val binders: SetTimeoutBinders[V]) extends AnyVal {

    @inline def apply[El <: ReactiveElement.Base](onNext: V => Unit): BinderWithStartStop[El] = binders --> onNext

  }

}
