package io.laminext.tailwind

import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveHtmlElement
import com.raquo.laminar.nodes.ReactiveSvgElement
import io.laminext.tailwind.ops.htmlelement.ReactiveHtmlElementTailwindOps
import io.laminext.tailwind.ops.signal.SignalOfBooleanTailwindOps
import io.laminext.tailwind.ops.svgelement.ReactiveSvgElementTailwindOps
import org.scalajs.dom

trait BaseSyntax {

  implicit def syntaxReactiveHtmlElementTailwind[T <: dom.html.Element](
    el: ReactiveHtmlElement[T]
  ): ReactiveHtmlElementTailwindOps[T] = new ReactiveHtmlElementTailwindOps[T](el)

  implicit def syntaxReactiveSvgElementTailwind[T <: dom.svg.Element](
    el: ReactiveSvgElement[T]
  ): ReactiveSvgElementTailwindOps[T] = new ReactiveSvgElementTailwindOps[T](el)

  implicit def syntaxSignalOfBooleanTailwind(s: Signal[Boolean]): SignalOfBooleanTailwindOps = new SignalOfBooleanTailwindOps(s)

}
