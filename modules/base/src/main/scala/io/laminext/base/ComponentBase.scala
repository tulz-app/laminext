package io.laminext.base

import com.raquo.laminar.api.L._
import com.raquo.laminar.modifiers.RenderableNode
import com.raquo.laminar.nodes.ChildNode.Base
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

trait ComponentBase[+R <: dom.html.Element] {

  val el: ReactiveHtmlElement[R]
  def ref: R = el.ref

  @inline def amend(mods: Modifier[ReactiveHtmlElement[R]]*): this.type = {
    el.amend(mods)
    this
  }

  @inline def amendThis(makeMod: this.type => Modifier[ReactiveHtmlElement[R]]): this.type = {
    el.amend(makeMod(this))
    this
  }

}

object ComponentBase {

  implicit def componentBaseToReactiveHtmlElement[R <: dom.html.Element]: RenderableNode[ComponentBase[R]] = new RenderableNode[ComponentBase[R]] {

    override def asNode(value: ComponentBase[R]): Base = value.el

    override def asNodeSeq(values: Seq[ComponentBase[R]]): Seq[Base] = values.map(_.el)

    override def asNodeIterable(values: Iterable[ComponentBase[R]]): Iterable[Base] = values.map(_.el)

    override def asNodeOption(value: Option[ComponentBase[R]]): Option[Base] = value.map(_.el)

  }

}
