package app.tulz.tailwind
package ops.htmltag

import theme.Theme
import com.raquo.laminar.api.L._
import app.tulz.laminar.ext._
import app.tulz.laminar.ext.AmendedHtmlTag
import com.raquo.laminar.builders.HtmlTag
import org.scalajs.dom

class HtmlTagTailwindOps[T <: dom.html.Element](tag: HtmlTag[T]) {

  @inline def btn: AmendedHtmlTag[T, AmButtonExpectSize] =
    tag.amend[AmButtonExpectSize](
      cls := Theme.current.button.common,
      cls := Theme.current.button.single,
      cls := Theme.current.animations.focusTransition
    )

}
