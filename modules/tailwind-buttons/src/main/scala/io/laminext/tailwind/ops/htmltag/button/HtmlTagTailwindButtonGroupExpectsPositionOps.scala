package io.laminext.tailwind
package ops.htmltag.button

import io.laminext.AmendedHtmlTagPartial
import io.laminext.tailwind.theme.Theme
import com.raquo.laminar.api.L._
import org.scalajs.dom

class HtmlTagTailwindButtonGroupExpectsPositionOps[T <: dom.html.Element](
  tag: AmendedHtmlTagPartial[T, AmButtonGroupExpectsPosition]
) {

  @inline def nth(index: Int, count: Int): AmendedHtmlTagPartial[T, AmButtonGroupExpectsSize] =
    if (count == 1) {
      tag.amend[AmButtonGroupExpectsSize](
        cls := Theme.current.button.group.single.classes
      )
    } else if (index == 0) {
      tag.amend[AmButtonGroupExpectsSize](
        cls := Theme.current.button.group.first.classes
      )
    } else if (index == count - 1) {
      tag.amend[AmButtonGroupExpectsSize](
        cls := Theme.current.button.group.last.classes
      )
    } else {
      tag.amend[AmButtonGroupExpectsSize](
        cls := Theme.current.button.group.inner.classes
      )
    }

  @inline def first: AmendedHtmlTagPartial[T, AmButtonGroupExpectsSize] =
    tag.amend[AmButtonGroupExpectsSize](
      cls := Theme.current.button.group.first.classes
    )

  @inline def inner: AmendedHtmlTagPartial[T, AmButtonGroupExpectsSize] =
    tag.amend[AmButtonGroupExpectsSize](
      cls := Theme.current.button.group.inner.classes
    )

  @inline def last: AmendedHtmlTagPartial[T, AmButtonGroupExpectsSize] =
    tag.amend[AmButtonGroupExpectsSize](
      cls := Theme.current.button.group.last.classes
    )

  @inline def single: AmendedHtmlTagPartial[T, AmButtonGroupExpectsSize] =
    tag.amend[AmButtonGroupExpectsSize](
      cls := Theme.current.button.group.single.classes
    )

}
