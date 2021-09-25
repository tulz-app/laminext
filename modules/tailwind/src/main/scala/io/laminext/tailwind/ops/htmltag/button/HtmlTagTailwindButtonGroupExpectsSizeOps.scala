package io.laminext.tailwind
package ops.htmltag.button

import io.laminext.AmendedHtmlTagPartial
import io.laminext.tailwind.theme.Theme
import com.raquo.laminar.api.L._
import org.scalajs.dom

class HtmlTagTailwindButtonGroupExpectsSizeOps[T <: dom.html.Element](
  tag: AmendedHtmlTagPartial[T, AmButtonGroupExpectsSize]
) {

  @inline def tiny: AmendedHtmlTagPartial[T, AmButtonGroupExpectsStyle] =
    tag.amend[AmButtonGroupExpectsStyle](
      cls := Theme.current.button.size.tiny.classes
    )

  @inline def xs: AmendedHtmlTagPartial[T, AmButtonGroupExpectsStyle]   =
    tag.amend[AmButtonGroupExpectsStyle](
      cls := Theme.current.button.size.xs.classes
    )

  @inline def sm: AmendedHtmlTagPartial[T, AmButtonGroupExpectsStyle]   =
    tag.amend[AmButtonGroupExpectsStyle](
      cls := Theme.current.button.size.sm.classes
    )

  @inline def md: AmendedHtmlTagPartial[T, AmButtonGroupExpectsStyle]   =
    tag.amend[AmButtonGroupExpectsStyle](
      cls := Theme.current.button.size.md.classes
    )

  @inline def lg: AmendedHtmlTagPartial[T, AmButtonGroupExpectsStyle]   =
    tag.amend[AmButtonGroupExpectsStyle](
      cls := Theme.current.button.size.lg.classes
    )

  @inline def xl: AmendedHtmlTagPartial[T, AmButtonGroupExpectsStyle]   =
    tag.amend[AmButtonGroupExpectsStyle](
      cls := Theme.current.button.size.xl.classes
    )

}
