package io.laminext.core
package ops.reactiveeventprop

import com.raquo.laminar.api.L._
import com.raquo.laminar.emitter.EventPropTransformation
import com.raquo.laminar.keys.ReactiveEventProp
import org.scalajs.dom

final class ReactiveEventPropOps[Ev <: dom.Event](underlying: ReactiveEventProp[Ev]) {

  @inline def mapToTrue: EventPropTransformation[Ev, Boolean] =
    underlying.mapToValue(true)

  @inline def mapToFalse: EventPropTransformation[Ev, Boolean] =
    underlying.mapToValue(false)

  @inline def mapToUnit: EventPropTransformation[Ev, Unit] =
    underlying.mapToValue((): Unit)

}
