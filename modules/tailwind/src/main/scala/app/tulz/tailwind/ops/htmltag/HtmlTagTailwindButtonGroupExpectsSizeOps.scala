package app.tulz.tailwind
package ops.htmltag

import theme.Theme
import app.tulz.laminar.ext.AmendedHtmlTag
import com.raquo.laminar.api.L._
import org.scalajs.dom

class HtmlTagTailwindButtonGroupExpectsSizeOps[T <: dom.html.Element](tag: AmendedHtmlTag[T, AmButtonGroupExpectsSize]) {

  @inline def tiny: AmendedHtmlTag[T, AmButtonGroupExpectsStyle] =
    tag.amend[AmButtonGroupExpectsStyle](
      cls := Theme.current.button.size.tiny
    )

  @inline def xs: AmendedHtmlTag[T, AmButtonGroupExpectsStyle] =
    tag.amend[AmButtonGroupExpectsStyle](
      cls := Theme.current.button.size.xs
    )

  @inline def sm: AmendedHtmlTag[T, AmButtonGroupExpectsStyle] =
    tag.amend[AmButtonGroupExpectsStyle](
      cls := Theme.current.button.size.sm
    )

  @inline def md: AmendedHtmlTag[T, AmButtonGroupExpectsStyle] =
    tag.amend[AmButtonGroupExpectsStyle](
      cls := Theme.current.button.size.md
    )

  @inline def lg: AmendedHtmlTag[T, AmButtonGroupExpectsStyle] =
    tag.amend[AmButtonGroupExpectsStyle](
      cls := Theme.current.button.size.lg
    )

  @inline def xl: AmendedHtmlTag[T, AmButtonGroupExpectsStyle] =
    tag.amend[AmButtonGroupExpectsStyle](
      cls := Theme.current.button.size.xl
    )

}
