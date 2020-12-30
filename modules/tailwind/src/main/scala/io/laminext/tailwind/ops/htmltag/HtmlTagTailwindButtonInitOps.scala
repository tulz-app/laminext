package io.laminext.tailwind
package ops.htmltag

import theme.Theme
import com.raquo.laminar.api.L._
import io.laminext.syntax.all._
import io.laminext.AmendedHtmlTag
import io.laminext.AmendedHtmlTagPartial
import com.raquo.laminar.builders.HtmlTag
import io.laminext.AmAny
import io.laminext.util.ClassJoin
import org.scalajs.dom

class HtmlTagTailwindButtonInitOps[T <: dom.html.Element](tag: HtmlTag[T]) {

  @inline def btn: AmendedHtmlTagPartial[T, AmButtonExpectSizeOrGroup] =
    tag.amendPartial[AmButtonExpectSizeOrGroup](
      cls := ClassJoin(
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
