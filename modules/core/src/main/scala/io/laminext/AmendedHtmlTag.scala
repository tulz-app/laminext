package io.laminext

import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveHtmlElement
import com.raquo.laminar.tags.HtmlTag
import org.scalajs.dom

class AmendedHtmlTag[+R <: dom.html.Element, AmType <: AmAny](
  original: HtmlTag[R],
  amendments: Seq[Mod[ReactiveHtmlElement[R]]]
) extends HtmlTag[R](original.name, original.void) {

  override def apply(modifiers: Mod[ReactiveHtmlElement[R]]*): ReactiveHtmlElement[R] = {
    original.apply(amendments ++ modifiers: _*)
  }

  def amend[NewAmType <: AmAny](mods: Mod[ReactiveHtmlElement[R]]*): AmendedHtmlTag[R, NewAmType] =
    new AmendedHtmlTag[R, NewAmType](this, amendments ++ mods)

}
