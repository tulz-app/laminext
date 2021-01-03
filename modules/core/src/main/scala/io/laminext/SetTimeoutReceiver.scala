package io.laminext

import com.raquo.airstream.core.Observer
import com.raquo.airstream.eventbus.EventBus
import com.raquo.laminar.nodes.ReactiveElement
import io.laminext.binders.BinderWithStartStop
import io.laminext.binders.SetTimeoutBinder

import scala.concurrent.duration.FiniteDuration

class SetTimeoutReceiver[V](
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
