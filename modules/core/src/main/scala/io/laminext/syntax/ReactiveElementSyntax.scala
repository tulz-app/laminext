package io.laminext.syntax

import com.raquo.laminar.nodes.ReactiveSvgElement
import io.laminext.ops.svgelement.ReactiveSvgElementOps
import org.scalajs.dom

trait ReactiveElementSyntax {

  implicit def syntaxReactiveSvgElement[T <: dom.svg.Element](
    el: ReactiveSvgElement[T]
  ): ReactiveSvgElementOps[T] = new ReactiveSvgElementOps[T](el)

}
