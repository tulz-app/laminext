package io.laminext.ops.element

import com.raquo.laminar.api.L._

object TextAreaElementOps {

  def changes(el: TextArea): EventStream[Unit] =
    EventStream
      .merge(
        el.events(onChange),
        el.events(onBlur),
        el.events(onInput),
        el.events(onPaste),
        el.events(onCut)
      )
      .mapToValue(())

}

class TextAreaElementOps(el: TextArea) {

  @inline def changes: EventStream[Unit] = TextAreaElementOps.changes(el)

  def valueSignal: Signal[String] =
    TextAreaElementOps.changes(el).mapTo(el.ref.value).toSignal(el.ref.value)

}
