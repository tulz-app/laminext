package io.laminext.core
package ops.svgelement

import com.raquo.laminar.api.L._
import com.raquo.laminar.keys.CompositeKey.CompositeValueMapper
import com.raquo.laminar.nodes.ReactiveSvgElement
import org.scalajs.dom

final class ReactiveSvgElementOps[T <: dom.svg.Element](el: ReactiveSvgElement[T]) {

  @inline def cls(klass: String): ReactiveSvgElement[T] =
    el.amend(svg.cls := klass)

  @inline def cls[V]($items: Source[V])(implicit valueMapper: CompositeValueMapper[V]): ReactiveSvgElement[T] =
    el.amend(svg.cls <-- $items)

}
