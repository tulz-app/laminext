package io.laminext.tailwind
package ops.htmltag.button

import io.laminext.syntax.all._
import io.laminext.AmendedHtmlTagPartial
import io.laminext.tailwind.theme.Theme
import com.raquo.laminar.api.L._
import org.scalajs.dom

class HtmlTagTailwindButtonExpectSizeOrGroupOps[T <: dom.html.Element](
  tag: AmendedHtmlTagPartial[T, AmButtonExpectSizeOrGroup]
) {

  @inline def group: AmendedHtmlTagPartial[T, AmButtonGroupExpectsPosition] =
    tag.amend[AmButtonGroupExpectsPosition](
      smartClass(
        Theme.current.button.single.classes       -> false,
        Theme.current.button.group.common.classes -> true
      )
    )

  @inline def tiny: AmendedHtmlTagPartial[T, AmButtonExpectsStyle] =
    tag.amend[AmButtonExpectsStyle](
      cls := Theme.current.button.size.tiny
    )

  @inline def xs: AmendedHtmlTagPartial[T, AmButtonExpectsStyle] =
    tag.amend[AmButtonExpectsStyle](
      cls := Theme.current.button.size.xs
    )

  @inline def sm: AmendedHtmlTagPartial[T, AmButtonExpectsStyle] =
    tag.amend[AmButtonExpectsStyle](
      cls := Theme.current.button.size.sm
    )

  @inline def md: AmendedHtmlTagPartial[T, AmButtonExpectsStyle] =
    tag.amend[AmButtonExpectsStyle](
      cls := Theme.current.button.size.md
    )

  @inline def lg: AmendedHtmlTagPartial[T, AmButtonExpectsStyle] =
    tag.amend[AmButtonExpectsStyle](
      cls := Theme.current.button.size.lg
    )

  @inline def xl: AmendedHtmlTagPartial[T, AmButtonExpectsStyle] =
    tag.amend[AmButtonExpectsStyle](
      cls := Theme.current.button.size.xl
    )

}
