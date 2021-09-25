package io.laminext.tailwind
package ops.htmltag.button

import io.laminext.AmendedHtmlTagPartial
import org.scalajs.dom

class HtmlTagTailwindButtonExpectsStyleOps[T <: dom.html.Element, Am <: AmButtonExpectsStyle](
  tag: AmendedHtmlTagPartial[T, Am]
) {

  @inline def fill: AmendedHtmlTagPartial[T, AmButtonFillExpectColor]               =
    tag.amended[AmButtonFillExpectColor]

  @inline def outline: AmendedHtmlTagPartial[T, AmButtonOutlineExpectColor]         =
    tag.amended[AmButtonOutlineExpectColor]

  @inline def text: AmendedHtmlTagPartial[T, AmButtonTransparentExpectColor]        =
    tag.amended[AmButtonTransparentExpectColor]

  @inline def transparent: AmendedHtmlTagPartial[T, AmButtonTransparentExpectColor] =
    tag.amended[AmButtonTransparentExpectColor]

}
