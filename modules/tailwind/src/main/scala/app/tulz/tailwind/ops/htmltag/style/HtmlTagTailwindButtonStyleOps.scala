package app.tulz.tailwind
package ops.htmltag.style

import theme.Theme
import theme.ButtonKinsColorStyles
import app.tulz.laminar.ext.AmendedHtmlTag
import org.scalajs.dom

abstract class HtmlTagTailwindButtonStyleOps[T <: dom.html.Element](
  tag: AmendedHtmlTag[T, _]
) {

  @inline def custom(tag: AmendedHtmlTag[T, _], styles: ButtonKinsColorStyles): AmendedHtmlTag[T, AmButtonWithStyle]

  @inline def black: AmendedHtmlTag[T, AmButtonWithStyle] =
    custom(tag, Theme.current.button.color.black)

  @inline def white: AmendedHtmlTag[T, AmButtonWithStyle] =
    custom(tag, Theme.current.button.color.white)

  @inline def red: AmendedHtmlTag[T, AmButtonWithStyle] =
    custom(tag, Theme.current.button.color.red)

  @inline def yellow: AmendedHtmlTag[T, AmButtonWithStyle] =
    custom(tag, Theme.current.button.color.yellow)

  @inline def green: AmendedHtmlTag[T, AmButtonWithStyle] =
    custom(tag, Theme.current.button.color.green)

  @inline def blue: AmendedHtmlTag[T, AmButtonWithStyle] =
    custom(tag, Theme.current.button.color.blue)

  @inline def indigo: AmendedHtmlTag[T, AmButtonWithStyle] =
    custom(tag, Theme.current.button.color.indigo)

  @inline def purple: AmendedHtmlTag[T, AmButtonWithStyle] =
    custom(tag, Theme.current.button.color.purple)

  @inline def pink: AmendedHtmlTag[T, AmButtonWithStyle] =
    custom(tag, Theme.current.button.color.pink)

}
