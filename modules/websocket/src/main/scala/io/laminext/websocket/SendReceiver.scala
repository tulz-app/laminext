package io.laminext.websocket

import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveElement
import org.scalajs.dom.raw

final class SendReceiver[Send](ws: WebSocket[_, Send]) {

  @inline def <--[El <: ReactiveElement[raw.HTMLElement]](observable: Observable[Send]): Binder[El] = {
    ws.send(observable)
  }

}
