package app.tulz.tailwind
package ops.htmlelement

import com.raquo.airstream.signal.Signal
import com.raquo.domtypes.generic.Modifier
import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

class ReactiveHtmlElementTailwindOps[T <: dom.html.Element](el: ReactiveHtmlElement[T]) {

  @inline def hiddenIf(s: Signal[Boolean]): Modifier[HtmlElement] =
    el.amend(
      cls <-- s.map { hiding =>
        Seq("hidden" -> hiding)
      }
    )

  @inline def visibleIf(s: Signal[Boolean]): Modifier[HtmlElement] =
    el.amend(
      cls <-- s.map { showing =>
        Seq("hidden" -> !showing)
      }
    )

}
