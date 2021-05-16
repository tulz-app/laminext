package io.laminext.tailwind
package ops.svgelement

import com.raquo.laminar.api.L
import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveSvgElement
import org.scalajs.dom

class ReactiveSvgElementTailwindOps[T <: dom.svg.Element](el: ReactiveSvgElement[T]) {

  def hiddenIf(s: Signal[Boolean]): ReactiveSvgElement[T] =
    el.amend(
      L.svg.cls <-- s.map { hiding =>
        Seq("hidden" -> hiding)
      }
    )

  def visibleIf(s: Signal[Boolean]): ReactiveSvgElement[T] =
    el.amend(
      L.svg.cls <-- s.map { showing =>
        Seq("hidden" -> !showing)
      }
    )

}
