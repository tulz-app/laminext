package app.tulz.tailwind
package ops.htmltag

import theme.Theme
import app.tulz.laminar.ext.AmendedHtmlTag
import com.raquo.laminar.api.L._
import org.scalajs.dom

class HtmlTagTailwindButtonOutlineOps[T <: dom.html.Element](tag: AmendedHtmlTag[T, AmButtonOutlineExpectColor]) {

  @inline def black: AmendedHtmlTag[T, AmButton] =
    tag.amend(
      cls := Theme.current.button.color.black.outline
    )

  @inline def white: AmendedHtmlTag[T, AmButton] =
    tag.amend(
      cls := Theme.current.button.color.white.outline
    )

  @inline def gray: AmendedHtmlTag[T, AmButton] =
    tag.amend(
      cls := Theme.current.button.color.gray.outline
    )

  @inline def red: AmendedHtmlTag[T, AmButton] =
    tag.amend(
      cls := Theme.current.button.color.red.outline
    )

  @inline def yellow: AmendedHtmlTag[T, AmButton] =
    tag.amend(
      cls := Theme.current.button.color.yellow.outline
    )

  @inline def green: AmendedHtmlTag[T, AmButton] =
    tag.amend(
      cls := Theme.current.button.color.green.outline
    )

  @inline def blue: AmendedHtmlTag[T, AmButton] =
    tag.amend(
      cls := Theme.current.button.color.blue.outline
    )

  @inline def indigo: AmendedHtmlTag[T, AmButton] =
    tag.amend(
      cls := Theme.current.button.color.indigo.outline
    )

  @inline def purple: AmendedHtmlTag[T, AmButton] =
    tag.amend(
      cls := Theme.current.button.color.purple.outline
    )

  @inline def pink: AmendedHtmlTag[T, AmButton] =
    tag.amend(
      cls := Theme.current.button.color.pink.outline
    )

}
