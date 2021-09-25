package io.laminext.tailwind
package ops.htmlelement

import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

class ReactiveHtmlElementTailwindOps[T <: dom.html.Element](el: ReactiveHtmlElement[T]) {

  @inline def hiddenIf(s: Signal[Boolean]): ReactiveHtmlElement[T]  =
    el.amend(
      cls <-- s.map { hiding =>
        Seq("hidden" -> hiding)
      }
    )

  @inline def visibleIf(s: Signal[Boolean]): ReactiveHtmlElement[T] =
    el.amend(
      cls <-- s.map { showing =>
        Seq("hidden" -> !showing)
      }
    )

}
