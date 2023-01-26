package io.laminext.syntax

import com.raquo.laminar.keys.EventProp
import io.laminext.core.ops.eventprop.EventPropOps
import org.scalajs.dom

trait EventPropSyntax {

  implicit def syntaxReactiveEventProp[Ev <: dom.Event](
    underlying: EventProp[Ev]
  ): EventPropOps[Ev] =
    new EventPropOps[Ev](underlying)

}
