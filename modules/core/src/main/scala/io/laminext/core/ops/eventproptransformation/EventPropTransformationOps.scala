package io.laminext.core
package ops.eventproptransformation

import com.raquo.laminar.api.L._
import org.scalajs.dom

final class EventPropTransformationOps[Ev <: dom.Event, V](underlying: EventProcessor[Ev, V]) {

  @inline def mapToTrue: EventProcessor[Ev, Boolean]  = underlying.mapToStrict(true)
  @inline def mapToFalse: EventProcessor[Ev, Boolean] = underlying.mapToStrict(false)
  @inline def mapToUnit: EventProcessor[Ev, Unit]     = underlying.mapToStrict(())

}
