package io.laminext.ui
package ops.htmltag.card

import com.raquo.laminar.builders.HtmlTag
import io.laminext.syntax.core._
import io.laminext.AmendedHtmlTagPartial
import org.scalajs.dom

class HtmlTagCardInitOps[T <: dom.html.Element](tag: HtmlTag[T]) {

  @inline def card: AmendedHtmlTagPartial[T, AmCard] =
    tag.amendedPartial[AmCard]

}
