package io.laminext.ui.syn

import com.raquo.laminar.builders.HtmlTag
import io.laminext.AmendedHtmlTagPartial
import io.laminext.ui.ops.htmltag.card.HtmlTagCardInitOps
import io.laminext.ui.ops.htmltag.card.HtmlTagCardOps
import io.laminext.ui.AmCard
import org.scalajs.dom

trait CardSyntax {

  implicit def syntaxHtmlTagTailwindCardInit[T <: dom.html.Element](
    tag: HtmlTag[T]
  ): HtmlTagCardInitOps[T] = new HtmlTagCardInitOps[T](tag)

  implicit def syntaxHtmlTagTailwindCard[T <: dom.html.Element](
    tag: AmendedHtmlTagPartial[T, AmCard]
  ): HtmlTagCardOps[T] = new HtmlTagCardOps[T](tag)

}