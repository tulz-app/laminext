package io.laminext.ui
package ops.htmltag.card

import io.laminext.AmAny
import io.laminext.AmendedHtmlTag
import io.laminext.AmendedHtmlTagPartial
import org.scalajs.dom

class HtmlTagCardOps[T <: dom.html.Element](
  tag: AmendedHtmlTagPartial[T, AmCard]
) {

  @inline def wrap: AmendedHtmlTag[T, AmAny] =
    tag.amend[AmAny](Theme.default.card.wrap).build

  @inline def body: AmendedHtmlTag[T, AmAny] =
    tag.amend[AmAny](Theme.default.card.body).build

  @inline def header: AmendedHtmlTag[T, AmAny] =
    tag.amend[AmAny](Theme.default.card.header).build

  @inline def footer: AmendedHtmlTag[T, AmAny] =
    tag.amend[AmAny](Theme.default.card.footer).build

  @inline def title: AmendedHtmlTag[T, AmAny] =
    tag.amend[AmAny](Theme.default.card.title).build

}
