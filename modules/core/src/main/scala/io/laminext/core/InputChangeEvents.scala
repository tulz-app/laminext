package io.laminext.core

import com.raquo.laminar.api.L._
import org.scalajs.dom.raw.Event

object InputChangeEvents {

  def apply(el: Element): EventStream[Event] =
    EventStream
      .merge(
        el.events(onChange),
        el.events(onBlur),
        el.events(onInput),
        el.events(onPaste),
        el.events(onCut)
      )

}
