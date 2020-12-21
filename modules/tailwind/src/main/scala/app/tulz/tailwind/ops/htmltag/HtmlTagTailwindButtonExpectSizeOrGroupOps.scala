package app.tulz.tailwind
package ops.htmltag

import theme.Theme
import app.tulz.laminar.ext.AmendedHtmlTag
import app.tulz.laminar.ext._
import com.raquo.laminar.api.L._
import org.scalajs.dom

class HtmlTagTailwindButtonExpectSizeOrGroupOps[T <: dom.html.Element](tag: AmendedHtmlTag[T, AmButtonExpectSizeOrGroup]) {

  @inline def group: AmendedHtmlTag[T, AmButtonGroupExpectsPosition] =
    tag.amend[AmButtonGroupExpectsPosition](
      cls := Seq(Theme.current.button.single -> false),
      cls := Seq(
        Theme.current.button.group.common,
        Theme.current.button.group.custom
      ).mkString(" ")
    )

  @inline def tiny: AmendedHtmlTag[T, AmButtonExpectsStyle] =
    tag.amend[AmButtonExpectsStyle](
      cls := Theme.current.button.size.tiny
    )

  @inline def xs: AmendedHtmlTag[T, AmButtonExpectsStyle] =
    tag.amend[AmButtonExpectsStyle](
      cls := Theme.current.button.size.xs
    )

  @inline def sm: AmendedHtmlTag[T, AmButtonExpectsStyle] =
    tag.amend[AmButtonExpectsStyle](
      cls := Theme.current.button.size.sm
    )

  @inline def md: AmendedHtmlTag[T, AmButtonExpectsStyle] =
    tag.amend[AmButtonExpectsStyle](
      cls := Theme.current.button.size.md
    )

  @inline def lg: AmendedHtmlTag[T, AmButtonExpectsStyle] =
    tag.amend[AmButtonExpectsStyle](
      cls := Theme.current.button.size.lg
    )

  @inline def xl: AmendedHtmlTag[T, AmButtonExpectsStyle] =
    tag.amend[AmButtonExpectsStyle](
      cls := Theme.current.button.size.xl
    )

}
