package app.tulz.tailwind
package ops.htmltag

import app.tulz.laminar.ext._
import app.tulz.laminar.ext.AmendedHtmlTag
import org.scalajs.dom

class HtmlTagTailwindButtonExpectsStyleOps[T <: dom.html.Element, Am <: AmButtonExpectsStyle](tag: AmendedHtmlTag[T, Am]) {

  @inline def fill: AmendedHtmlTag[T, AmButtonFillExpectColor] =
    tag.amended[AmButtonFillExpectColor]

  @inline def outline: AmendedHtmlTag[T, AmButtonOutlineExpectColor] =
    tag.amended[AmButtonOutlineExpectColor]

  @inline def text: AmendedHtmlTag[T, AmButtonTransparentExpectColor] =
    tag.amended[AmButtonTransparentExpectColor]

  @inline def transparent: AmendedHtmlTag[T, AmButtonTransparentExpectColor] =
    tag.amended[AmButtonTransparentExpectColor]

}
