package io.laminext.core
package ops.htmlelement

import com.raquo.laminar.api.L._
import org.scalajs.dom.ext._
import org.scalajs.dom.raw.Event
import org.scalajs.dom.raw.File

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

  @inline def files: Signal[Seq[File]] = files(identity)

  def files(
    changeStreamTransform: EventStream[Event] => EventStream[Event]
  ): Signal[Seq[File]] =
    changes(changeStreamTransform)
      .mapTo(
        new EasySeq[File](el.ref.files.length, el.ref.files.apply).toSeq
      )
      .toSignal(
        new EasySeq[File](el.ref.files.length, el.ref.files.apply).toSeq
      )

}
