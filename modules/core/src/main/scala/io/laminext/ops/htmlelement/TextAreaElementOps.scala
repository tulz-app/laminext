package io.laminext
package ops.htmlelement

import com.raquo.laminar.api.L._
import org.scalajs.dom.raw.Event

class TextAreaElementOps(el: TextArea) {

  @inline def changes: EventStream[Event] = InputChangeEvents.changes(el)

  @inline def value: Signal[String] =
    changes.mapTo(el.ref.value).toSignal(el.ref.value)

}
