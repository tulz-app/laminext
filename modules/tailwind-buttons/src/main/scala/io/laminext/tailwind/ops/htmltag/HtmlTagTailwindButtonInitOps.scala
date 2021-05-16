package io.laminext.tailwind
package ops.htmltag

import com.raquo.laminar.api.L._
import io.laminext.syntax.core._
import io.laminext.AmendedHtmlTag
import com.raquo.laminar.builders.HtmlTag
import io.laminext.AmAny
import org.scalajs.dom

class HtmlTagTailwindButtonInitOps[T <: dom.html.Element](tag: HtmlTag[T]) {

  @inline def btn: AmendedHtmlTag[T, AmButtonExpectSizeOrGroup] =
    tag.amended[AmButtonExpectSizeOrGroup]

  @inline def groupBtn: AmendedHtmlTag[T, AmButtonExpectSizeOrGroup] =
    tag.amended[AmButtonExpectSizeOrGroup]

  @inline def buttonGroup: AmendedHtmlTag[T, AmAny] =
    tag.amend[AmAny](cls("button-group"))

}
