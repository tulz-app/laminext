package app.tulz.tailwind
package ops.htmltag.button

import app.tulz.laminext.AmendedHtmlTagPartial
import app.tulz.tailwind.theme.Theme
import com.raquo.laminar.api.L._
import org.scalajs.dom

class HtmlTagTailwindButtonGroupExpectsSizeOps[T <: dom.html.Element](tag: AmendedHtmlTagPartial[T, AmButtonGroupExpectsSize]) {

  @inline def tiny: AmendedHtmlTagPartial[T, AmButtonGroupExpectsStyle] =
    tag.amend[AmButtonGroupExpectsStyle](
      cls := Theme.current.button.size.tiny
    )

  @inline def xs: AmendedHtmlTagPartial[T, AmButtonGroupExpectsStyle] =
    tag.amend[AmButtonGroupExpectsStyle](
      cls := Theme.current.button.size.xs
    )

  @inline def sm: AmendedHtmlTagPartial[T, AmButtonGroupExpectsStyle] =
    tag.amend[AmButtonGroupExpectsStyle](
      cls := Theme.current.button.size.sm
    )

  @inline def md: AmendedHtmlTagPartial[T, AmButtonGroupExpectsStyle] =
    tag.amend[AmButtonGroupExpectsStyle](
      cls := Theme.current.button.size.md
    )

  @inline def lg: AmendedHtmlTagPartial[T, AmButtonGroupExpectsStyle] =
    tag.amend[AmButtonGroupExpectsStyle](
      cls := Theme.current.button.size.lg
    )

  @inline def xl: AmendedHtmlTagPartial[T, AmButtonGroupExpectsStyle] =
    tag.amend[AmButtonGroupExpectsStyle](
      cls := Theme.current.button.size.xl
    )

}
