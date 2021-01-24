package io.laminext.syntax

import com.raquo.laminar.emitter.EventPropTransformation
import io.laminext.core.ops.eventproptransformation.EventPropTransformationOps
import org.scalajs.dom

trait EventPropTransformationSyntax {

  implicit def syntaxEventPropTransformation[Ev <: dom.Event, V](
    underlying: EventPropTransformation[Ev, V]
  ): EventPropTransformationOps[Ev, V] =
    new EventPropTransformationOps[Ev, V](underlying)

}
