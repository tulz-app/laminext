package io.laminext.websocket

import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveElement
import org.scalajs.dom.raw

final class ReceivedBinders[Receive](ws: WebSocket[Receive, _]) {

  @inline def -->[El <: ReactiveElement[raw.HTMLElement]](
    observer: Observer[Receive]
  ): Binder[El] =
    ws.receivedBinder(observer.onNext, observer.onError)

  @inline def -->[El <: ReactiveElement[raw.HTMLElement]](
    onNext: Receive => Unit,
    onDecodeError: Throwable => Unit
  ): Binder[El] = {
    ws.receivedBinder(onNext, onDecodeError)
  }

  @inline def -->[El <: ReactiveElement[raw.HTMLElement]](
    eventBus: EventBus[Receive]
  ): Binder[El] = -->(eventBus.writer)

  @inline def stream: EventStream[Receive] = ws.receivedStream

}
