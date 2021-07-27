package io.laminext.core

import com.raquo.laminar.api.L._
import org.scalajs.dom.raw.Event

object InputChangeEvents {

  def apply(
    el: Element,
    changeStreamTransform: EventStream[Event] => EventStream[Event] = identity
  ): EventStream[Event] =
    EventStream
      .merge(
        changeStreamTransform(
          EventStream.merge(
            el.events(onChange),
            el.events(onInput)
          )
        ),
        el.events(onBlur),
        el.events(onPaste),
        el.events(onCut)
      )

}
