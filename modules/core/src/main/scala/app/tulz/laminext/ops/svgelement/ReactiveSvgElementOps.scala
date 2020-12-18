package app.tulz.laminext.ops.svgelement

import com.raquo.airstream.core.Observable
import com.raquo.laminar.api.L._
import com.raquo.laminar.keys.CompositeAttr.CompositeValueMapper
import com.raquo.laminar.nodes.ReactiveSvgElement
import org.scalajs.dom

class ReactiveSvgElementOps[T <: dom.svg.Element](el: ReactiveSvgElement[T]) {

  @inline def cls(klass: String): ReactiveSvgElement[T] = {
    el.amend(svg.cls := klass)
  }

  @inline def cls[V]($items: Observable[V])(implicit valueMapper: CompositeValueMapper[V]): ReactiveSvgElement[T] = {
    el.amend(svg.cls <-- $items)
  }

}
