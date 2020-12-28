package app.tulz.laminext.ops.reactiveeventprop

import app.tulz.laminext.EventPropToStream
import com.raquo.laminar.api.L._
import com.raquo.laminar.emitter.EventPropTransformation
import com.raquo.laminar.keys.ReactiveEventProp
import com.raquo.laminar.nodes.ReactiveElement
import org.scalajs.dom

final class ReactiveEventPropOps[Ev <: dom.Event](underlying: ReactiveEventProp[Ev]) {

  @inline def mapToTrue: EventPropTransformation[Ev, Boolean] = underlying.mapToValue(true)

  @inline def mapToFalse: EventPropTransformation[Ev, Boolean] = underlying.mapToValue(false)

  @inline def mapToUnit: EventPropTransformation[Ev, Unit] = underlying.mapToValue((): Unit)

  def stream[El <: ReactiveElement.Base]: EventPropToStream[Ev, Ev] =
    new EventPropToStream(
      key = underlying,
      shouldUseCapture = false,
      shouldPreventDefault = false,
      shouldStopPropagation = false,
      transform = identity
    )

}
