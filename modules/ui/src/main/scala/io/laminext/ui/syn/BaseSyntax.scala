package io.laminext.ui.syn

import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveHtmlElement
import io.laminext.ui.ops.htmlelement.ReactiveHtmlElementUiOps
import org.scalajs.dom

trait BaseSyntax {

  implicit def syntaxReactiveHtmlElementUi[T <: dom.html.Element](
    el: ReactiveHtmlElement[T]
  ): ReactiveHtmlElementUiOps[T] = new ReactiveHtmlElementUiOps[T](el)

}
