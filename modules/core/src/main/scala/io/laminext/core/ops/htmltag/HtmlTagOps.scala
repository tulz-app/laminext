package io.laminext.core
package ops.htmltag

import com.raquo.laminar.api.L._
import io.laminext.AmendedHtmlTag
import io.laminext.AmendedHtmlTagPartial
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.nodes.ReactiveHtmlElement
import io.laminext.AmAny
import org.scalajs.dom

final class HtmlTagOps[T <: dom.html.Element](tag: HtmlTag[T]) {

  @inline def amended[AmType <: AmAny]: AmendedHtmlTag[T, AmType] =
    new AmendedHtmlTag[T, AmType](tag, Seq.empty)

  @inline def amendedPartial[AmType <: AmAny]: AmendedHtmlTagPartial[T, AmType] =
    new AmendedHtmlTagPartial[T, AmType](tag, Seq.empty)

  @inline def amend[AmType <: AmAny](
    mods: Modifier[ReactiveHtmlElement[T]]*
  ): AmendedHtmlTag[T, AmType] =
    new AmendedHtmlTag[T, AmType](tag, mods)

  @inline def amendPartial[AmType <: AmAny](
    mods: Modifier[ReactiveHtmlElement[T]]*
  ): AmendedHtmlTagPartial[T, AmType] =
    new AmendedHtmlTagPartial[T, AmType](tag, mods)

}
