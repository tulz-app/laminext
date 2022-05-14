package io.laminext.core

import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveElement
import io.laminext.core.binders.BinderWithStartStop
import io.laminext.core.binders.ResizeObserverBinder
import io.laminext.domext.ResizeObserverEntry
import org.scalajs.dom

object ResizeObserverBinders {

  @inline def -->[El <: ReactiveElement[dom.HTMLElement]](sink: Sink[ResizeObserverEntry]): BinderWithStartStop[El] = {
    new ResizeObserverBinder(t => sink.toObserver.onNext(t))
  }

  @inline def -->[El <: ReactiveElement[dom.HTMLElement]](onNext: ResizeObserverEntry => Unit): BinderWithStartStop[El] = {
    new ResizeObserverBinder(onNext)
  }

  @inline def bind[El <: ReactiveElement[dom.HTMLElement]](sink: Sink[ResizeObserverEntry]): BinderWithStartStop[El] = -->(sink)

  @inline def bind[El <: ReactiveElement[dom.HTMLElement]](onNext: ResizeObserverEntry => Unit): BinderWithStartStop[El] = -->(onNext)

}
