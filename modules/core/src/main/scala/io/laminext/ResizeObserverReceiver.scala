package io.laminext

import com.raquo.airstream.core.Observer
import com.raquo.airstream.eventbus.EventBus
import com.raquo.laminar.nodes.ReactiveElement
import io.laminext.binders.BinderWithStartStop
import io.laminext.binders.ResizeObserverBinder
import io.laminext.domext.ResizeObserverEntry

object ResizeObserverReceiver {

  @inline def -->[El <: ReactiveElement[org.scalajs.dom.raw.HTMLElement]](observer: Observer[ResizeObserverEntry]): BinderWithStartStop[El] = {
    new ResizeObserverBinder(t => observer.onNext(t))
  }

  @inline def -->[El <: ReactiveElement[org.scalajs.dom.raw.HTMLElement]](onNext: ResizeObserverEntry => Unit): BinderWithStartStop[El] = {
    new ResizeObserverBinder(onNext)
  }

  @inline def -->[El <: ReactiveElement[org.scalajs.dom.raw.HTMLElement]](eventBus: EventBus[ResizeObserverEntry]): BinderWithStartStop[El] = {
    new ResizeObserverBinder(t => eventBus.writer.onNext(t))
  }

}
