package io.laminext.tailwind
package ops.htmltag.button

import io.laminext.AmendedHtmlTagPartial
import org.scalajs.dom

class HtmlTagTailwindButtonGroupExpectsStyleOps[T <: dom.html.Element](
  tag: AmendedHtmlTagPartial[T, AmButtonGroupExpectsStyle]
) {

  @inline def fill: AmendedHtmlTagPartial[T, AmButtonGroupFillExpectColor] =
    tag.amended[AmButtonGroupFillExpectColor]

  @inline def outline: AmendedHtmlTagPartial[T, AmButtonGroupOutlineExpectColor] =
    tag.amended[AmButtonGroupOutlineExpectColor]

  @inline def text: AmendedHtmlTagPartial[T, AmButtonGroupTransparentExpectColor] =
    tag.amended[AmButtonGroupTransparentExpectColor]

  @inline def transparent: AmendedHtmlTagPartial[T, AmButtonGroupTransparentExpectColor] =
    tag.amended[AmButtonGroupTransparentExpectColor]

}
