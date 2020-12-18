package app.tulz.laminar.ext

import com.raquo.laminar.api.L._
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

class AmendedHtmlTag[+R <: dom.html.Element, AmType <: AmAny](
  original: HtmlTag[R],
  amendments: Seq[Mod[ReactiveHtmlElement[R]]]
) extends HtmlTag[R](original.name, original.void) {

  override def apply(modifiers: Mod[ReactiveHtmlElement[R]]*): ReactiveHtmlElement[R] = {
    original.apply(modifiers ++ amendments: _*)
  }

  def amend(mods: Mod[ReactiveHtmlElement[R]]*): AmendedHtmlTag[R, AmType] =
    new AmendedHtmlTag[R, AmType](this, amendments ++ mods)

}
