package io.laminext.tailwind
package ops.htmltag.button.style

import theme.ButtonStyleColors
import io.laminext.AmendedHtmlTag
import io.laminext.AmendedHtmlTagPartial
import com.raquo.laminar.api.L._
import io.laminext.util.ClassJoin
import org.scalajs.dom

class HtmlTagTailwindButtonTransparentOps[T <: dom.html.Element](
  tag: AmendedHtmlTagPartial[T, AmButtonTransparentExpectColor]
) extends HtmlTagTailwindButtonStyleOps[T] {

  @inline def custom(
    styles: ButtonStyleColors
  ): AmendedHtmlTag[T, AmButtonWithStyle] =
    tag
      .amend(
        cls := ClassJoin(styles.base, styles.transparent, styles.single.transparent)
      ).build
}
