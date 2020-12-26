package app.tulz.tailwind.ops
package htmltag.card

import app.tulz.laminar.ext._
import app.tulz.laminext.AmendedHtmlTag
import app.tulz.laminext.AmendedHtmlTagPartial
import app.tulz.tailwind.AmCard
import app.tulz.tailwind.AmCardBody
import app.tulz.tailwind.AmCardFooter
import app.tulz.tailwind.AmCardHeader
import app.tulz.tailwind.AmCardTitle
import app.tulz.tailwind.theme.Theme
import com.raquo.laminar.api.L._
import org.scalajs.dom

class HtmlTagTailwindCardOps[T <: dom.html.Element](tag: AmendedHtmlTagPartial[T, AmCard]) {

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
