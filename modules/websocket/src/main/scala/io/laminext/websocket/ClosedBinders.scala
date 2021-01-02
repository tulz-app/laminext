package io.laminext.websocket

import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveElement
import org.scalajs.dom.raw

final class ClosedBinders[Receive](ws: WebSocket[Receive, _]) {

  @inline def -->[El <: ReactiveElement[raw.HTMLElement]](
    observer: Observer[raw.WebSocket]
  ): Binder[El] =
    ws.closedBinder(observer.onNext)

  @inline def -->[El <: ReactiveElement[raw.HTMLElement]](
    onNext: raw.WebSocket => Unit
  ): Binder[El] = {
    ws.closedBinder(onNext)
  }

  @inline def -->[El <: ReactiveElement[raw.HTMLElement]](
    eventBus: EventBus[raw.WebSocket]
  ): Binder[El] = -->(eventBus.writer)

}
