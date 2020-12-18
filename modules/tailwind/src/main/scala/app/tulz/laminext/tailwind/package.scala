package app.tulz.laminext

import app.tulz.laminext.tailwind.ops.htmlelement.ReactiveHtmlElementTailwindOps
import app.tulz.laminext.tailwind.ops.svgelement.ReactiveSvgElementTailwindOps
import com.raquo.laminar.nodes.ReactiveHtmlElement
import com.raquo.laminar.nodes.ReactiveSvgElement
import org.scalajs.dom

package object tailwind {

  implicit def reactiveHtmlElementTailwind[T <: dom.html.Element](el: ReactiveHtmlElement[T]): ReactiveHtmlElementTailwindOps[T] = new ReactiveHtmlElementTailwindOps[T](el)
  implicit def reactiveSvgElementTailwind[T <: dom.svg.Element](el: ReactiveSvgElement[T]): ReactiveSvgElementTailwindOps[T]     = new ReactiveSvgElementTailwindOps[T](el)

}
