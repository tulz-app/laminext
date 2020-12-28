package app.tulz.tailwind
package ops.svgelement

import com.raquo.laminar.api.L.{cls => _, _}
import com.raquo.laminar.api.L.svg._
import com.raquo.airstream.signal.Signal
import com.raquo.domtypes.generic.Modifier
import com.raquo.laminar.nodes.ReactiveSvgElement
import org.scalajs.dom

class ReactiveSvgElementTailwindOps[T <: dom.svg.Element](el: ReactiveSvgElement[T]) {

  def hiddenIf(s: Signal[Boolean]): ReactiveSvgElement[T] =
    el.amend(
      cls <-- s.map { hiding =>
        Seq("hidden" -> hiding)
      }
    )

  def visibleIf(s: Signal[Boolean]): ReactiveSvgElement[T] =
    el.amend(
      cls <-- s.map { showing =>
        Seq("hidden" -> !showing)
      }
    )

}
