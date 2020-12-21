package app.tulz.tailwind
package ops.htmltag

import app.tulz.laminar.ext.AmendedHtmlTag
import org.scalajs.dom

class HtmlTagTailwindButtonGroupExpectsStyleOps[T <: dom.html.Element](tag: AmendedHtmlTag[T, AmButtonGroupExpectsStyle]) {

  @inline def fill: AmendedHtmlTag[T, AmButtonGroupFillExpectColor] =
    tag.amended[AmButtonGroupFillExpectColor]

  @inline def outline: AmendedHtmlTag[T, AmButtonGroupOutlineExpectColor] =
    tag.amended[AmButtonGroupOutlineExpectColor]

  @inline def text: AmendedHtmlTag[T, AmButtonGroupTransparentExpectColor] =
    tag.amended[AmButtonGroupTransparentExpectColor]

  @inline def transparent: AmendedHtmlTag[T, AmButtonGroupTransparentExpectColor] =
    tag.amended[AmButtonGroupTransparentExpectColor]

}
