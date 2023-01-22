package io.laminext.syntax

import com.raquo.laminar.tags.SvgTag
import io.laminext.core.ops.svgtag.SvgTagOps
import org.scalajs.dom

trait SvgTagSyntax {

  implicit def syntaxSvgTag[T <: dom.svg.Element](
    tag: SvgTag[T]
  ): SvgTagOps[T] = new SvgTagOps[T](tag)

}
