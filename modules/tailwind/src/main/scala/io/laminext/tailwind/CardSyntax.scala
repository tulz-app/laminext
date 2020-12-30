package io.laminext.tailwind

import io.laminext.AmendedHtmlTagPartial
import io.laminext.tailwind.ops.htmltag._
import io.laminext.tailwind.ops.htmltag.card.HtmlTagTailwindCardOps
import com.raquo.laminar.builders.HtmlTag
import org.scalajs.dom

trait CardSyntax {

  implicit def syntaxHtmlTagTailwindCardInit[T <: dom.html.Element](
    tag: HtmlTag[T]
  ): HtmlTagTailwindCardInitOps[T] = new HtmlTagTailwindCardInitOps[T](tag)

  implicit def syntaxHtmlTagTailwindCard[T <: dom.html.Element](
    tag: AmendedHtmlTagPartial[T, AmCard]
  ): HtmlTagTailwindCardOps[T] = new HtmlTagTailwindCardOps[T](tag)

}
