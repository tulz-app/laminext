package io.laminext.syntax

import com.raquo.laminar.api.L._
import io.laminext.core.ops.eventproptransformation.EventPropTransformationOps
import org.scalajs.dom

trait EventPropTransformationSyntax {

  implicit def syntaxEventPropTransformation[Ev <: dom.Event, V](
    underlying: EventProcessor[Ev, V]
  ): EventPropTransformationOps[Ev, V] =
    new EventPropTransformationOps[Ev, V](underlying)

}
