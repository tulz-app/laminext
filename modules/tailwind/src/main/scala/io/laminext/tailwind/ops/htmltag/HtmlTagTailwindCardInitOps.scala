package io.laminext.tailwind
package ops.htmltag

import io.laminext.syntax.all._
import io.laminext.AmendedHtmlTagPartial
import com.raquo.laminar.builders.HtmlTag
import org.scalajs.dom

class HtmlTagTailwindCardInitOps[T <: dom.html.Element](tag: HtmlTag[T]) {

  @inline def card: AmendedHtmlTagPartial[T, AmCard] =
    tag.amendedPartial[AmCard]

}
