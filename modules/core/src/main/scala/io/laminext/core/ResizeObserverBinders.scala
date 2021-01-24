package io.laminext.core

import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveElement
import io.laminext.core.binders.BinderWithStartStop
import io.laminext.core.binders.ResizeObserverBinder
import io.laminext.domext.ResizeObserverEntry

object ResizeObserverBinders {

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
