package io.laminext.syntax

import com.raquo.laminar.nodes.ReactiveHtmlElement
import io.laminext.ops.htmlelement.ReactiveHtmlElementOps
import org.scalajs.dom

trait ReactiveHtmlTagSyntax {

  implicit def syntaxReactiveHtmlElement[T <: dom.html.Element](
    el: ReactiveHtmlElement[T]
  ): ReactiveHtmlElementOps[T] = new ReactiveHtmlElementOps[T](el)

}
