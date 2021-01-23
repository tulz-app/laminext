package io.laminext.tailwind.ops
package htmltag.card

import io.laminext.syntax.core._
import io.laminext.AmendedHtmlTag
import io.laminext.AmendedHtmlTagPartial
import io.laminext.tailwind.AmCard
import io.laminext.tailwind.AmCardBody
import io.laminext.tailwind.AmCardFooter
import io.laminext.tailwind.AmCardHeader
import io.laminext.tailwind.AmCardTitle
import io.laminext.tailwind.theme.Theme
import com.raquo.laminar.api.L._
import io.laminext.AmAny
import org.scalajs.dom

class HtmlTagTailwindCardOps[T <: dom.html.Element](
  tag: AmendedHtmlTagPartial[T, AmCard]
) {

  @inline def wrap: AmendedHtmlTag[T, AmAny] =
    tag
      .amend[AmAny](
        cls := Theme.current.card.wrap.classes
      ).build

  @inline def body: AmendedHtmlTag[T, AmCardBody] =
    tag
      .amend[AmCardBody](
        cls := Theme.current.card.body.classes
      ).build

  @inline def header: AmendedHtmlTag[T, AmCardHeader] =
    tag
      .amend[AmCardHeader](
        cls := Theme.current.card.header.classes
      ).build

  @inline def footer: AmendedHtmlTag[T, AmCardFooter] =
    tag
      .amend[AmCardFooter](
        cls := Theme.current.card.footer.classes
      ).build

  @inline def title: AmendedHtmlTag[T, AmCardTitle] =
    tag
      .amend[AmCardTitle](
        cls := Theme.current.card.title.classes
      ).build

}
