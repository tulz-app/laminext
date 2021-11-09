package io.laminext.core
package ops.htmlelement

import com.raquo.laminar.api.L._
import org.scalajs.dom.Event
import org.scalajs.dom.FileList

final class InputElementOps(el: Input) {

  @inline def changes: EventStream[Event] = InputChangeEvents(el)

  @inline def changes(
    changeStreamTransform: EventStream[Event] => EventStream[Event]
  ): EventStream[Event] = InputChangeEvents(el, changeStreamTransform)

  @inline def value: Signal[String] = value(identity)

  def value(
    changeStreamTransform: EventStream[Event] => EventStream[Event]
  ): Signal[String] =
    changes(changeStreamTransform).mapTo(el.ref.value).toSignal(el.ref.value)

  @inline def checked: Signal[Boolean] = checked(identity)

  def checked(
    changeStreamTransform: EventStream[Event] => EventStream[Event]
  ): Signal[Boolean] =
    changes(changeStreamTransform).mapTo(el.ref.checked).toSignal(el.ref.checked)

  @inline def files: Signal[FileList] = files(identity)

  def files(
    changeStreamTransform: EventStream[Event] => EventStream[Event]
  ): Signal[FileList] =
    changes(changeStreamTransform)
      .mapTo(el.ref.files)
      .toSignal(el.ref.files)

}
