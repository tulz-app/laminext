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

  @inline def -->[El <: ReactiveElement.Base](observer: Observer[V]): BinderWithStartStop[El] = {
    new SetTimeoutBinder(value, timeout, t => observer.onNext(t))
  }

  @inline def -->[El <: ReactiveElement.Base](onNext: V => Unit): BinderWithStartStop[El] = {
    new SetTimeoutBinder(value, timeout, onNext)
  }

  @inline def -->[El <: ReactiveElement.Base](eventBus: EventBus[V]): BinderWithStartStop[El] = {
    new SetTimeoutBinder(value, timeout, t => eventBus.writer.onNext(t))
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
