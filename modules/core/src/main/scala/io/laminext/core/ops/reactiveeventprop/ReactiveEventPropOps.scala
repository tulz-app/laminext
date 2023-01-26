package io.laminext.core
package ops.eventprop

import com.raquo.laminar.api.L._
import com.raquo.laminar.keys.EventProp
import org.scalajs.dom

final class EventPropOps[Ev <: dom.Event](underlying: EventProp[Ev]) {

  @inline def mapToTrue: EventProcessor[Ev, Boolean] = underlying.mapToStrict(true)

  @inline def mapToFalse: EventProcessor[Ev, Boolean] = underlying.mapToStrict(false)

}
