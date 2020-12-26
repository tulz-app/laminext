package app.tulz.tailwind
package ops.htmltag

import theme.Theme
import com.raquo.laminar.api.L._
import app.tulz.laminar.ext._
import app.tulz.laminext.AmendedHtmlTag
import app.tulz.laminext.AmendedHtmlTagPartial
import com.raquo.laminar.builders.HtmlTag
import org.scalajs.dom

class HtmlTagTailwindButtonInitOps[T <: dom.html.Element](tag: HtmlTag[T]) {

  @inline def btn: AmendedHtmlTagPartial[T, AmButtonExpectSizeOrGroup] =
    tag.amendPartial[AmButtonExpectSizeOrGroup](
      cls := classJoin(
        Theme.current.button.common.classes,
        Theme.current.button.disabled,
        Theme.current.animation.focusTransition
      ),
      smartClass(
        Theme.current.button.single.classes       -> true,
        Theme.current.button.group.common.classes -> false
      )
    )

  @inline def buttonGroup: AmendedHtmlTag[T, AmAny] =
    tag.amend[AmAny](
      cls := Theme.current.buttonGroup.base
    )

}
