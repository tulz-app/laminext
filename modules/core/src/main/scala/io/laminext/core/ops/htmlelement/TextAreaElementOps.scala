package io.laminext.core
package ops.htmlelement

import com.raquo.laminar.api.L._
import org.scalajs.dom.raw.Event

class TextAreaElementOps(el: TextArea) {

  @inline def changes: EventStream[Event] = InputChangeEvents(el)
  @inline def changes(
    changeStreamTransform: EventStream[Event] => EventStream[Event]
  ): EventStream[Event] = InputChangeEvents(el, changeStreamTransform)

  @inline def value: Signal[String] = value(identity)

  def value(
    changeStreamTransform: EventStream[Event] => EventStream[Event]
  ): Signal[String] =
    changes(changeStreamTransform).mapTo(el.ref.value).toSignal(el.ref.value)

}
