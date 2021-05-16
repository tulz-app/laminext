package io.laminext.base

import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

trait ComponentBase[+R <: dom.html.Element] {

  val el: ReactiveHtmlElement[R]

  @inline def amend(mods: Modifier[ReactiveHtmlElement[R]]*): this.type = {
    el.amend(mods)
    this
  }

  @inline def amendThis(makeMod: ReactiveHtmlElement[R] => Modifier[ReactiveHtmlElement[R]]): this.type = {
    el.amendThis(makeMod)
    this
  }

}

object ComponentBase {

  @inline implicit def componentBaseToReactiveHtmlElement[R <: dom.html.Element](
    component: ComponentBase[R]
  ): ReactiveHtmlElement[R] = component.el

}
