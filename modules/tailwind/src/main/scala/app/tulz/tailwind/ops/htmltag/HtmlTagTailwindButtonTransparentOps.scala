package app.tulz.tailwind
package ops.htmltag

import theme.Theme
import app.tulz.laminar.ext.AmendedHtmlTag
import com.raquo.laminar.api.L._
import org.scalajs.dom

class HtmlTagTailwindButtonTransparentOps[T <: dom.html.Element](tag: AmendedHtmlTag[T, AmButtonTransparentExpectColor]) {

  @inline def black: AmendedHtmlTag[T, AmButton] =
    tag.amend(
      cls := Theme.current.button.color.black.transparent
    )

  @inline def white: AmendedHtmlTag[T, AmButton] =
    tag.amend(
      cls := Theme.current.button.color.white.transparent
    )

  @inline def gray: AmendedHtmlTag[T, AmButton] =
    tag.amend(
      cls := Theme.current.button.color.gray.transparent
    )

  @inline def red: AmendedHtmlTag[T, AmButton] =
    tag.amend(
      cls := Theme.current.button.color.red.transparent
    )

  @inline def yellow: AmendedHtmlTag[T, AmButton] =
    tag.amend(
      cls := Theme.current.button.color.yellow.transparent
    )

  @inline def green: AmendedHtmlTag[T, AmButton] =
    tag.amend(
      cls := Theme.current.button.color.green.transparent
    )

  @inline def blue: AmendedHtmlTag[T, AmButton] =
    tag.amend(
      cls := Theme.current.button.color.blue.transparent
    )

  @inline def indigo: AmendedHtmlTag[T, AmButton] =
    tag.amend(
      cls := Theme.current.button.color.indigo.transparent
    )

  @inline def purple: AmendedHtmlTag[T, AmButton] =
    tag.amend(
      cls := Theme.current.button.color.purple.transparent
    )

  @inline def pink: AmendedHtmlTag[T, AmButton] =
    tag.amend(
      cls := Theme.current.button.color.pink.transparent
    )

}
