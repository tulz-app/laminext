package app.tulz.tailwind
package ops.htmltag

import theme.Theme
import app.tulz.laminar.ext.AmendedHtmlTag
import com.raquo.laminar.api.L._
import org.scalajs.dom

class HtmlTagTailwindButtonGroupExpectsPositionOps[T <: dom.html.Element](tag: AmendedHtmlTag[T, AmButtonGroupExpectsPosition]) {

  @inline def first: AmendedHtmlTag[T, AmButtonGroupExpectsSize] =
    tag.amend[AmButtonGroupExpectsSize](
      cls := Theme.current.button.group.first
    )

  @inline def inner: AmendedHtmlTag[T, AmButtonGroupExpectsSize] =
    tag.amend[AmButtonGroupExpectsSize](
      cls := Theme.current.button.group.inner
    )

  @inline def last: AmendedHtmlTag[T, AmButtonGroupExpectsSize] =
    tag.amend[AmButtonGroupExpectsSize](
      cls := Theme.current.button.group.last
    )

  @inline def single: AmendedHtmlTag[T, AmButtonGroupExpectsSize] =
    tag.amend[AmButtonGroupExpectsSize](
      cls := Theme.current.button.group.single
    )

}
