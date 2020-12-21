package app.tulz.tailwind
package ops.htmltag.style

import theme.ButtonKinsColorStyles
import app.tulz.laminar.ext.AmendedHtmlTag
import com.raquo.laminar.api.L._
import org.scalajs.dom

class HtmlTagTailwindButtonGroupOutlineOps[T <: dom.html.Element](
  tag: AmendedHtmlTag[T, AmButtonGroupOutlineExpectColor]
) extends HtmlTagTailwindButtonStyleOps[T](tag) {

  @inline def custom(tag: AmendedHtmlTag[T, _], styles: ButtonKinsColorStyles): AmendedHtmlTag[T, AmButtonWithStyle] =
    tag.amend(
      cls := Seq(styles.common.outline, styles.group.outline)
    )

}
