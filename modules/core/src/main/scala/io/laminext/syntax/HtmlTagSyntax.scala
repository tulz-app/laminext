package io.laminext.syntax

import com.raquo.laminar.builders.HtmlTag
import io.laminext.core.ops.htmltag.HtmlTagOps
import org.scalajs.dom

trait HtmlTagSyntax {

  implicit def syntaxHtmlTag[T <: dom.html.Element](
    tag: HtmlTag[T]
  ): HtmlTagOps[T] = new HtmlTagOps[T](tag)

}
