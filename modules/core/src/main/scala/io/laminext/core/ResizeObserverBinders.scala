package io.laminext.core

import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveElement
import io.laminext.core.binders.BinderWithStartStop
import io.laminext.core.binders.ResizeObserverBinder
import org.scalajs.dom
import org.scalajs.dom.ResizeObserverEntry
import org.scalajs.dom.ResizeObserverOptions

import scala.scalajs.js

class ResizeObserverBinders(
  options: js.UndefOr[ResizeObserverOptions] = js.undefined
) {

  @inline def -->[El <: ReactiveElement[dom.HTMLElement]](sink: Sink[ResizeObserverEntry]): BinderWithStartStop[El] = {
    new ResizeObserverBinder(t => sink.toObserver.onNext(t), options)
  }

  @inline def -->[El <: ReactiveElement[dom.HTMLElement]](onNext: ResizeObserverEntry => Unit): BinderWithStartStop[El] = {
    new ResizeObserverBinder(onNext, options)
  }

  @inline def bind[El <: ReactiveElement[dom.HTMLElement]](sink: Sink[ResizeObserverEntry]): BinderWithStartStop[El] = -->(sink)

  @inline def bind[El <: ReactiveElement[dom.HTMLElement]](onNext: ResizeObserverEntry => Unit): BinderWithStartStop[El] = -->(onNext)

}
