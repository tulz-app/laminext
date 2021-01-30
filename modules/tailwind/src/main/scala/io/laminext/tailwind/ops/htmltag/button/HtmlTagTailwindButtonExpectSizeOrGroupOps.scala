package io.laminext.tailwind
package ops.htmltag.button

import com.raquo.laminar.api.L._
import io.laminext.AmendedHtmlTagPartial
import io.laminext.tailwind.theme.Theme
import org.scalajs.dom

class HtmlTagTailwindButtonExpectSizeOrGroupOps[T <: dom.html.Element](
  tag: AmendedHtmlTagPartial[T, AmButtonExpectSizeOrGroup]
) {

  @inline def group: AmendedHtmlTagPartial[T, AmButtonGroupExpectsPosition] = {
    tag.amend[AmButtonGroupExpectsPosition](
      cls(Theme.current.button.group.common.classes)
    )
  }

  @inline def tiny: AmendedHtmlTagPartial[T, AmButtonExpectsStyle] =
    tag.amend[AmButtonExpectsStyle](
      cls(Theme.current.button.size.tiny),
      cls(Theme.current.button.single.classes)
    )

  @inline def xs: AmendedHtmlTagPartial[T, AmButtonExpectsStyle] =
    tag.amend[AmButtonExpectsStyle](
      cls(Theme.current.button.size.xs),
      cls(Theme.current.button.single.classes)
    )

  @inline def sm: AmendedHtmlTagPartial[T, AmButtonExpectsStyle] =
    tag.amend[AmButtonExpectsStyle](
      cls(Theme.current.button.size.sm),
      cls(Theme.current.button.single.classes)
    )

  @inline def md: AmendedHtmlTagPartial[T, AmButtonExpectsStyle] =
    tag.amend[AmButtonExpectsStyle](
      cls(Theme.current.button.size.md),
      cls(Theme.current.button.single.classes)
    )

  @inline def lg: AmendedHtmlTagPartial[T, AmButtonExpectsStyle] =
    tag.amend[AmButtonExpectsStyle](
      cls(Theme.current.button.size.lg),
      cls(Theme.current.button.single.classes)
    )

  @inline def xl: AmendedHtmlTagPartial[T, AmButtonExpectsStyle] =
    tag.amend[AmButtonExpectsStyle](
      cls(Theme.current.button.size.xl),
      cls(Theme.current.button.single.classes)
    )

}
