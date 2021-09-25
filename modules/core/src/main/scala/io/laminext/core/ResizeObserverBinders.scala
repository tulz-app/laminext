package io.laminext.core

import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveElement
import io.laminext.core.binders.BinderWithStartStop
import io.laminext.core.binders.ResizeObserverBinder
import io.laminext.domext.ResizeObserverEntry

object ResizeObserverBinders {

  @inline def -->[El <: ReactiveElement[org.scalajs.dom.raw.HTMLElement]](sink: Sink[ResizeObserverEntry]): BinderWithStartStop[El]     = {
    new ResizeObserverBinder(t => sink.toObserver.onNext(t))
  }

  @inline def -->[El <: ReactiveElement[org.scalajs.dom.raw.HTMLElement]](onNext: ResizeObserverEntry => Unit): BinderWithStartStop[El] = {
    new ResizeObserverBinder(onNext)
  }

  @inline def bind[El <: ReactiveElement[org.scalajs.dom.raw.HTMLElement]](sink: Sink[ResizeObserverEntry]): BinderWithStartStop[El]    = -->(sink)

  @inline def bind[El <: ReactiveElement[org.scalajs.dom.raw.HTMLElement]](onNext: ResizeObserverEntry => Unit): BinderWithStartStop[El] = -->(onNext)

}
