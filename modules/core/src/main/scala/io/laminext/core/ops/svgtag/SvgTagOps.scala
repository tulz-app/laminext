package io.laminext.core
package ops.svgtag

import com.raquo.laminar.api.L._
import io.laminext.AmendedSvgTag
import com.raquo.laminar.tags.SvgTag
import com.raquo.laminar.nodes.ReactiveSvgElement
import org.scalajs.dom

final class SvgTagOps[T <: dom.svg.Element](tag: SvgTag[T]) {

  @inline def amend[AmType](
    mods: Modifier[ReactiveSvgElement[T]]*
  ): AmendedSvgTag[T] =
    new AmendedSvgTag[T](tag.name, tag.void, mods)

}
