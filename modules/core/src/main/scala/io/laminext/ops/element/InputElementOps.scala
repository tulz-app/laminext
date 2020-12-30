package io.laminext.ops.element

import com.raquo.laminar.api.L._

object InputElementOps {

  def changes(el: Input): EventStream[Unit] = {
    EventStream
      .merge(
        el.events(onChange),
        el.events(onBlur),
        el.events(onInput),
        el.events(onPaste),
        el.events(onCut)
      )
      .mapToValue((): Unit)
  }

}

final class InputElementOps(el: Input) {

  @inline def changes: EventStream[Unit] = InputElementOps.changes(el)

  def valueSignal: Signal[String] =
    InputElementOps.changes(el).mapTo(el.ref.value).toSignal(el.ref.value)

  def checkedSignal: Signal[Boolean] =
    InputElementOps.changes(el).mapTo(el.ref.checked).toSignal(el.ref.checked)

}
