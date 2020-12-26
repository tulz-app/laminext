package app.tulz.tailwind
package ops.htmltag

import app.tulz.laminar.ext._
import app.tulz.laminext.AmendedHtmlTagPartial
import com.raquo.laminar.builders.HtmlTag
import org.scalajs.dom

class HtmlTagTailwindCardInitOps[T <: dom.html.Element](tag: HtmlTag[T]) {

  @inline def card: AmendedHtmlTagPartial[T, AmCard] = tag.amendedPartial[AmCard]

}
