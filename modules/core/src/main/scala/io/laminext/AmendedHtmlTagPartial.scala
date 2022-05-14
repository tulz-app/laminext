package io.laminext

import com.raquo.laminar.api.L._
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom
import scala.annotation.implicitNotFound

class AmendedHtmlTagPartial[R <: dom.html.Element, AmType <: AmAny](
  original: HtmlTag[R],
  private var amendments: Seq[Mod[ReactiveHtmlElement[R]]]
) {

  final def apply(modifiers: Mod[ReactiveHtmlElement[R]]*)(implicit cantApply: AmendedHtmlTagPartialCantApply): ReactiveHtmlElement[R] = {
    throw new RuntimeException("cannot be called")
  }

  def amend[NewAmType <: AmAny](mods: Mod[ReactiveHtmlElement[R]]*): AmendedHtmlTagPartial[R, NewAmType] = {
    amendments = amendments ++ mods
    this.asInstanceOf[AmendedHtmlTagPartial[R, NewAmType]]
  }
  def amended[NewAmType <: AmAny]: AmendedHtmlTagPartial[R, NewAmType]                                   =
    this.asInstanceOf[AmendedHtmlTagPartial[R, NewAmType]]

  def build[NewAmType <: AmAny]: AmendedHtmlTag[R, NewAmType] = new AmendedHtmlTag[R, NewAmType](original, amendments)

}

@implicitNotFound(msg = "cannot apply a partially built tag")
sealed trait AmendedHtmlTagPartialCantApply
