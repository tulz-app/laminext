package app.tulz.laminext.ops.htmlelement

import com.raquo.laminar.api.L._
import com.raquo.airstream.core.Observable
import com.raquo.airstream.signal.Signal
import com.raquo.domtypes.generic.Modifier
import com.raquo.laminar.keys.CompositeAttr.CompositeValueMapper
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

class ReactiveHtmlElementOps[T <: dom.html.Element](el: ReactiveHtmlElement[T]) {

  @inline def cls(klass: String): ReactiveHtmlElement[T] = {
    el.amend(com.raquo.laminar.api.L.cls := klass)
  }

  @inline def cls[V]($items: Observable[V])(implicit valueMapper: CompositeValueMapper[V]): ReactiveHtmlElement[T] = {
    el.amend(com.raquo.laminar.api.L.cls <-- $items)
  }

}
