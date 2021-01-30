package io.laminext.tailwind

import com.raquo.laminar.nodes.ReactiveHtmlElement
import com.raquo.laminar.nodes.ReactiveSvgElement
import io.laminext.tailwind.ops.htmlelement.ButtonTailwindFileInputOps
import io.laminext.tailwind.ops.htmlelement.ReactiveHtmlElementTailwindOps
import io.laminext.tailwind.ops.svgelement.ReactiveSvgElementTailwindOps
import org.scalajs.dom

trait FileInputSyntax {

  implicit def syntaxButtonTailwindFileInput[T <: dom.html.Button](
    el: ReactiveHtmlElement[T]
  ): ButtonTailwindFileInputOps[T] = new ButtonTailwindFileInputOps[T](el)

}
