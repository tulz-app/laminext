package app.tulz.laminext.ops.htmltag

import app.tulz.laminar.ext.AmAny
import app.tulz.laminar.ext.AmendedHtmlTag
import com.raquo.domtypes.generic.Modifier
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

class HtmlTagOps[T <: dom.html.Element](tag: HtmlTag[T]) {

  @inline def amend[AmType <: AmAny]: AmendedHtmlTag[T, AmType] =
    new AmendedHtmlTag[T, AmType](tag, Seq.empty)

  @inline def amend[AmType <: AmAny](mods: Modifier[ReactiveHtmlElement[T]]*): AmendedHtmlTag[T, AmType] =
    new AmendedHtmlTag[T, AmType](tag, mods)

}
