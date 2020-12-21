package app.tulz.tailwind
package ops.htmltag

import theme.Theme
import com.raquo.laminar.api.L._
import app.tulz.laminar.ext._
import app.tulz.laminar.ext.AmendedHtmlTag
import com.raquo.laminar.builders.HtmlTag
import org.scalajs.dom

class HtmlTagTailwindOps[T <: dom.html.Element](tag: HtmlTag[T]) {

  @inline def btn: AmendedHtmlTag[T, AmButtonExpectSizeOrGroup] =
    tag.amend[AmButtonExpectSizeOrGroup](
      cls := Seq(
        Theme.current.button.common,
        Theme.current.button.custom,
        Theme.current.button.disabled,
        Theme.current.button.single,
        Theme.current.animation.focusTransition
      ).mkString(" ")
    )

}
