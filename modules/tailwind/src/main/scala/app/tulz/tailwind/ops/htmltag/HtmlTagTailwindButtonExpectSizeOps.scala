package app.tulz.tailwind
package ops.htmltag

import theme.Theme
import app.tulz.laminar.ext.AmendedHtmlTag
import app.tulz.laminar.ext._
import com.raquo.laminar.api.L._
import org.scalajs.dom

class HtmlTagTailwindButtonExpectSizeOps[T <: dom.html.Element](tag: AmendedHtmlTag[T, AmButtonExpectSize]) {

  @inline def tiny: AmendedHtmlTag[T, AmButton] =
    tag.amend[AmButton](
      cls := Theme.current.button.size.tiny
    )

  @inline def xs: AmendedHtmlTag[T, AmButton] =
    tag.amend[AmButton](
      cls := Theme.current.button.size.xs
    )

  @inline def sm: AmendedHtmlTag[T, AmButton] =
    tag.amend[AmButton](
      cls := Theme.current.button.size.sm
    )

  @inline def md: AmendedHtmlTag[T, AmButton] =
    tag.amend[AmButton](
      cls := Theme.current.button.size.md
    )

  @inline def lg: AmendedHtmlTag[T, AmButton] =
    tag.amend[AmButton](
      cls := Theme.current.button.size.lg
    )

  @inline def xl: AmendedHtmlTag[T, AmButton] =
    tag.amend[AmButton](
      cls := Theme.current.button.size.xl
    )

}
