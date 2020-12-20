package app.tulz.tailwind
package ops.htmltag

import theme.Theme
import app.tulz.laminar.ext.AmendedHtmlTag
import app.tulz.laminar.ext._
import com.raquo.laminar.api.L._
import org.scalajs.dom

class HtmlTagTailwindGroupButtonOps[T <: dom.html.Element](tag: AmendedHtmlTag[T, AmGroupButton]) {

  @inline def groupFirst: AmendedHtmlTag[T, AmButton] =
    tag.amend[AmButton](
      cls := Theme.current.button.group.first
    )

  @inline def groupInner: AmendedHtmlTag[T, AmButton] =
    tag.amend[AmButton](
      cls := Theme.current.button.group.inner
    )

  @inline def groupLast: AmendedHtmlTag[T, AmButton] =
    tag.amend[AmButton](
      cls := Theme.current.button.group.last
    )

  @inline def groupSingle: AmendedHtmlTag[T, AmButton] =
    tag.amend[AmButton](
      cls := Theme.current.button.group.single
    )

}
