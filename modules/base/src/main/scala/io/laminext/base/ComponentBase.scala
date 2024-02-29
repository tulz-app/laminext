package io.laminext.base

import com.raquo.laminar
import com.raquo.laminar.modifiers.RenderableNode
import com.raquo.laminar.nodes.ChildNode.Base
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

trait ComponentBase[+R <: dom.html.Element] {

  def el: ReactiveHtmlElement[R]
  def ref: R = el.ref

}

object ComponentBase {

  implicit def componentBaseToReactiveHtmlElement[R <: dom.html.Element]: RenderableNode[ComponentBase[R]] = new RenderableNode[ComponentBase[R]] {

    override def asNode(value: ComponentBase[R]): Base = value.el

    override def asNodeSeq(values: laminar.Seq[ComponentBase[R]]): laminar.Seq[Base] = values.map(_.el)

    override def asNodeOption(value: Option[ComponentBase[R]]): Option[Base] = value.map(_.el)

  }

}
