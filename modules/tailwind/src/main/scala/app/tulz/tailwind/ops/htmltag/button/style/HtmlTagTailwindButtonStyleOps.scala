package app.tulz.tailwind
package ops.htmltag.button.style

import app.tulz.laminext.AmendedHtmlTag
import theme.Theme
import theme.ButtonStyleColors
import org.scalajs.dom

abstract class HtmlTagTailwindButtonStyleOps[T <: dom.html.Element] {

  @inline def custom(styles: ButtonStyleColors): AmendedHtmlTag[T, AmButtonWithStyle]

  @inline def black: AmendedHtmlTag[T, AmButtonWithStyle] =
    custom(Theme.current.button.color.black)

  @inline def white: AmendedHtmlTag[T, AmButtonWithStyle] =
    custom(Theme.current.button.color.white)

  @inline def red: AmendedHtmlTag[T, AmButtonWithStyle] =
    custom(Theme.current.button.color.red)

  @inline def yellow: AmendedHtmlTag[T, AmButtonWithStyle] =
    custom(Theme.current.button.color.yellow)

  @inline def green: AmendedHtmlTag[T, AmButtonWithStyle] =
    custom(Theme.current.button.color.green)

  @inline def blue: AmendedHtmlTag[T, AmButtonWithStyle] =
    custom(Theme.current.button.color.blue)

  @inline def indigo: AmendedHtmlTag[T, AmButtonWithStyle] =
    custom(Theme.current.button.color.indigo)

  @inline def purple: AmendedHtmlTag[T, AmButtonWithStyle] =
    custom(Theme.current.button.color.purple)

  @inline def pink: AmendedHtmlTag[T, AmButtonWithStyle] =
    custom(Theme.current.button.color.pink)

}
