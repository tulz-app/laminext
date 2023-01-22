package io.laminext.tailwind
package ops.htmltag

import io.laminext.syntax.core._
import io.laminext.AmendedHtmlTagPartial
import com.raquo.laminar.tags.HtmlTag
import org.scalajs.dom

class HtmlTagTailwindCardInitOps[T <: dom.html.Element](tag: HtmlTag[T]) {

  @inline def card: AmendedHtmlTagPartial[T, AmCard] =
    tag.amendedPartial[AmCard]

}
