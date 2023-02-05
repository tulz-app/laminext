package io.laminext.ui.syn

import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveHtmlElement
import com.raquo.laminar.nodes.ReactiveSvgElement
import io.laminext.ui.ops.htmlelement.ReactiveHtmlElementUiOps
import io.laminext.ui.ops.signal.SignalOfBooleanUiOps
import io.laminext.ui.ops.svgelement.ReactiveSvgElementUiOps
import org.scalajs.dom

trait BaseSyntax {

  implicit def syntaxReactiveHtmlElementUi[T <: dom.html.Element](
    el: ReactiveHtmlElement[T]
  ): ReactiveHtmlElementUiOps[T] = new ReactiveHtmlElementUiOps[T](el)

  implicit def syntaxReactiveSvgElementUi[T <: dom.svg.Element](
    el: ReactiveSvgElement[T]
  ): ReactiveSvgElementUiOps[T] = new ReactiveSvgElementUiOps[T](el)

  implicit def syntaxSignalOfBooleanUi(s: Signal[Boolean]): SignalOfBooleanUiOps = new SignalOfBooleanUiOps(s)

}
