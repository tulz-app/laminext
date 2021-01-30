package io.laminext.core
package ops.htmlelement

import com.raquo.laminar.api.L._
import org.scalajs.dom.ext._
import org.scalajs.dom.raw.Event
import org.scalajs.dom.raw.File

final class InputElementOps(el: Input) {

  @inline def changes: EventStream[Event] = InputChangeEvents(el)

  @inline def value: Signal[String] =
    changes.mapTo(el.ref.value).toSignal(el.ref.value)

  @inline def checked: Signal[Boolean] =
    changes.mapTo(el.ref.checked).toSignal(el.ref.checked)

  @inline def files: Signal[Seq[File]] =
    changes
      .mapTo(
        new EasySeq[File](el.ref.files.length, el.ref.files.apply).toSeq
      ).toSignal(
        new EasySeq[File](el.ref.files.length, el.ref.files.apply).toSeq
      )

}
