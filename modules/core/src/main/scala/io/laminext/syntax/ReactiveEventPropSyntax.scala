package io.laminext.syntax

import com.raquo.laminar.keys.ReactiveEventProp
import io.laminext.core.ops.reactiveeventprop.ReactiveEventPropOps
import org.scalajs.dom

trait ReactiveEventPropSyntax {

  implicit def syntaxReactiveEventProp[Ev <: dom.Event](
    underlying: ReactiveEventProp[Ev]
  ): ReactiveEventPropOps[Ev] =
    new ReactiveEventPropOps[Ev](underlying)

}
