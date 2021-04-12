package io.laminext.base

import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

trait ComponentBase[+R <: dom.html.Element] {

  val el: ReactiveHtmlElement[R]

}

object ComponentBase {

  @inline implicit def componentBaseToReactiveHtmlElement[R <: dom.html.Element](
    component: ComponentBase[R]
  ): ReactiveHtmlElement[R] = component.el

}
