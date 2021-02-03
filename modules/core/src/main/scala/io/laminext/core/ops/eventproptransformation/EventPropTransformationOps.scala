package io.laminext.core
package ops.eventproptransformation

import com.raquo.laminar.emitter.EventPropTransformation
import org.scalajs.dom

final class EventPropTransformationOps[Ev <: dom.Event, V](underlying: EventPropTransformation[Ev, V]) {

  @inline def mapToTrue: EventPropTransformation[Ev, Boolean]  = underlying.mapToStrict(true)
  @inline def mapToFalse: EventPropTransformation[Ev, Boolean] = underlying.mapToStrict(false)
  @inline def mapToUnit: EventPropTransformation[Ev, Unit]     = underlying.mapToStrict(())

}
