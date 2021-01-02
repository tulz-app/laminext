package io.laminext.websocket

import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveElement
import org.scalajs.dom.raw

final class ErrorBinders[Receive](ws: WebSocket[Receive, _]) {

  @inline def -->[El <: ReactiveElement[raw.HTMLElement]](
    observer: Observer[Throwable]
  ): Binder[El] =
    ws.errorBinder(observer.onNext)

  @inline def -->[El <: ReactiveElement[raw.HTMLElement]](
    onNext: Throwable => Unit
  ): Binder[El] = {
    ws.errorBinder(onNext)
  }

  @inline def -->[El <: ReactiveElement[raw.HTMLElement]](
    eventBus: EventBus[Throwable]
  ): Binder[El] = -->(eventBus.writer)

}
