package app.tulz.tailwind
package ops.htmltag

import com.raquo.laminar.api.L._
import app.tulz.laminar.ext.AmendedHtmlTag
import org.scalajs.dom

class HtmlTagTailwindButtonWithStyleOps[T <: dom.html.Element, Am <: AmButtonWithStyle](tag: AmendedHtmlTag[T, Am]) {

  @inline def shadow: AmendedHtmlTag[T, AmButtonWithStyle] =
    tag.amend[AmButtonWithStyle](
      cls := "shadow"
    )

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

}
