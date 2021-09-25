package io.laminext.core
package ops.htmlelement

import com.raquo.laminar.api.L._
import com.raquo.laminar.keys.CompositeKey.CompositeValueMapper
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

final class ReactiveHtmlElementOps[T <: dom.html.Element](el: ReactiveHtmlElement[T]) {

  @inline def cls(className: String): ReactiveHtmlElement[T]                                                  = {
    el.amend(com.raquo.laminar.api.L.cls := className)
  }

  @inline def cls[V](value: Source[V])(implicit valueMapper: CompositeValueMapper[V]): ReactiveHtmlElement[T] = {
    el.amend(com.raquo.laminar.api.L.cls <-- value)
  }

}
