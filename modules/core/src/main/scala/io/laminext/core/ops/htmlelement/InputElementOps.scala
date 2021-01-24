package io.laminext.core
package ops.htmlelement

import com.raquo.laminar.api.L._
import org.scalajs.dom.raw.Event

final class InputElementOps(el: Input) {

  @inline def changes: EventStream[Event] = InputChangeEvents(el)

  @inline def value: Signal[String] =
    changes.mapTo(el.ref.value).toSignal(el.ref.value)

  @inline def checked: Signal[Boolean] =
    changes.mapTo(el.ref.checked).toSignal(el.ref.checked)

}
