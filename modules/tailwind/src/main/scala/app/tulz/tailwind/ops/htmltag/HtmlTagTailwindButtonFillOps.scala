package app.tulz.tailwind
package ops.htmltag

import theme.Theme
import app.tulz.laminar.ext.AmendedHtmlTag
import com.raquo.laminar.api.L._
import org.scalajs.dom

class HtmlTagTailwindButtonFillOps[T <: dom.html.Element](tag: AmendedHtmlTag[T, AmButtonFillExpectColor]) {

  @inline def black: AmendedHtmlTag[T, AmButton] =
    tag.amend(
      cls := Theme.current.button.color.black.fill
    )

  @inline def white: AmendedHtmlTag[T, AmButton] =
    tag.amend(
      cls := Theme.current.button.color.white.fill
    )

  @inline def gray: AmendedHtmlTag[T, AmButton] =
    tag.amend(
      cls := Theme.current.button.color.gray.fill
    )

  @inline def red: AmendedHtmlTag[T, AmButton] =
    tag.amend(
      cls := Theme.current.button.color.red.fill
    )

  @inline def yellow: AmendedHtmlTag[T, AmButton] =
    tag.amend(
      cls := Theme.current.button.color.yellow.fill
    )

  @inline def green: AmendedHtmlTag[T, AmButton] =
    tag.amend(
      cls := Theme.current.button.color.green.fill
    )

  @inline def blue: AmendedHtmlTag[T, AmButton] =
    tag.amend(
      cls := Theme.current.button.color.blue.fill
    )

  @inline def indigo: AmendedHtmlTag[T, AmButton] =
    tag.amend(
      cls := Theme.current.button.color.indigo.fill
    )

  @inline def purple: AmendedHtmlTag[T, AmButton] =
    tag.amend(
      cls := Theme.current.button.color.purple.fill
    )

  @inline def pink: AmendedHtmlTag[T, AmButton] =
    tag.amend(
      cls := Theme.current.button.color.pink.fill
    )

}
