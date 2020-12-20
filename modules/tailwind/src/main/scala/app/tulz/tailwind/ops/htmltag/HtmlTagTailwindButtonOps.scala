package app.tulz.tailwind
package ops.htmltag

import theme.Theme
import com.raquo.laminar.api.L._
import app.tulz.laminar.ext._
import app.tulz.laminar.ext.AmendedHtmlTag
import org.scalajs.dom

class HtmlTagTailwindButtonOps[T <: dom.html.Element, Am <: AmButton](tag: AmendedHtmlTag[T, Am]) {

//  @inline def disabled: AmendedHtmlTag[T, AmButton] =
//    tag.amend[AmButton](
//      cls := Seq(
//        Theme.current.button.disabled
//      )
//    )
//
//  @inline def disabled(d: Signal[Boolean]): AmendedHtmlTag[T, AmButton] =
//    tag.amend[AmButton](
//      cls <-- d.cls(
//        Theme.current.button.disabled
//      )
//    )

  @inline def group: AmendedHtmlTag[T, AmGroupButton] =
    tag.amend[AmGroupButton](
      cls := Seq(
        Theme.current.button.single       -> false,
        Theme.current.button.group.common -> true
      )
    )

  @inline def fill: AmendedHtmlTag[T, AmButtonFillExpectColor] =
    tag.amended[AmButtonFillExpectColor]

  @inline def outline: AmendedHtmlTag[T, AmButtonOutlineExpectColor] =
    tag.amended[AmButtonOutlineExpectColor]

  @inline def text: AmendedHtmlTag[T, AmButtonTransparentExpectColor] =
    tag.amended[AmButtonTransparentExpectColor]

  @inline def transparrent: AmendedHtmlTag[T, AmButtonTransparentExpectColor] =
    tag.amended[AmButtonTransparentExpectColor]

//  @inline def textGray100: AmendedHtmlTag[T, AmButton] =
//    tag.amend[AmButton](
//      cls := Classes.btn.textGray100
//    )
//
//  @inline def textGray900: AmendedHtmlTag[T, AmButton] =
//    tag.amend[AmButton](
//      cls := Classes.btn.textGray900
//    )
//
//  @inline def bgGray800: AmendedHtmlTag[T, AmButton] =
//    tag.amend[AmButton](
//      cls := Classes.btn.bgGray800
//    )
//
//  @inline def textWhite: AmendedHtmlTag[T, AmButton] =
//    tag.amend[AmButton](
//      cls := Classes.btn.textWhite
//    )
//
//  @inline def lightBorder: AmendedHtmlTag[T, AmButton] =
//    tag.amend[AmButton](
//      cls := Classes.btn.lightBorder
//    )
//
//  @inline def white: AmendedHtmlTag[T, AmButton] =
//    tag.amend[AmButton](
//      cls := Classes.btn.white
//    )
//
//  @inline def indigo: AmendedHtmlTag[T, AmButton] =
//    tag.amend[AmButton](
//      cls := Classes.btn.indigo
//    )
//
//  @inline def lightIndigo: AmendedHtmlTag[T, AmButton] =
//    tag.amend[AmButton](
//      cls := Classes.btn.indigoLight
//    )
//
//  @inline def blue: AmendedHtmlTag[T, AmButton] =
//    tag.amend[AmButton](
//      cls := Classes.btn.blue
//    )
//
//  @inline def outlineBlue: AmendedHtmlTag[T, AmButton] =
//    tag.amend[AmButton](
//      cls := Classes.btn.outlineBlue
//    )
//
//  @inline def outlineRed: AmendedHtmlTag[T, AmButton] =
//    tag.amend[AmButton](
//      cls := Classes.btn.outlineRed
//    )
//
//  @inline def outlineRedDarker: AmendedHtmlTag[T, AmButton] =
//    tag.amend[AmButton](
//      cls := Classes.btn.outlineRedDarker
//    )
//
//  @inline def outlineGreen: AmendedHtmlTag[T, AmButton] =
//    tag.amend[AmButton](
//      cls := Classes.btn.outlineGreen
//    )
//
//  @inline def lightCoolGray: AmendedHtmlTag[T, AmButton] =
//    tag.amend[AmButton](
//      cls := Classes.btn.lightCoolGray
//    )
//
//  @inline def lightBlue: AmendedHtmlTag[T, AmButton] =
//    tag.amend[AmButton](
//      cls := Classes.btn.lightBlue
//    )
//
//  @inline def shadow: AmendedHtmlTag[T, AmButton] =
//    tag.amend[AmButton](
//      cls := "shadow"
//    )

}
