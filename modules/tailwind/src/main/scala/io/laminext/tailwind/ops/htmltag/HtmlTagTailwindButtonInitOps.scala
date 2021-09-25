package io.laminext.tailwind
package ops.htmltag

import theme.Theme
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._
import io.laminext.AmendedHtmlTag
import io.laminext.AmendedHtmlTagPartial
import com.raquo.laminar.builders.HtmlTag
import io.laminext.AmAny
import io.laminext.util.ClassJoin
import org.scalajs.dom

class HtmlTagTailwindButtonInitOps[T <: dom.html.Element](tag: HtmlTag[T]) {

  @inline def btn: AmendedHtmlTagPartial[T, AmButtonExpectSizeOrGroup] =
    tag.amendPartial[AmButtonExpectSizeOrGroup](
      cls(Theme.current.button.common.classes),
      cls(Theme.current.button.disabled.classes),
      cls(Theme.current.animation.focusTransition.classes)
    )

  @inline def buttonGroup: AmendedHtmlTag[T, AmAny]                    =
    tag.amend[AmAny](
      cls(Theme.current.buttonGroup.base)
    )

}
