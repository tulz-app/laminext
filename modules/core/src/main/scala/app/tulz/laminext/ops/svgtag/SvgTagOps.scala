package app.tulz.laminext.ops.svgtag

import app.tulz.laminext.AmendedSvgTag
import com.raquo.domtypes.generic.Modifier
import com.raquo.laminar.builders.SvgTag
import com.raquo.laminar.nodes.ReactiveSvgElement
import org.scalajs.dom

final class SvgTagOps[T <: dom.svg.Element](tag: SvgTag[T]) {

  @inline def amend[AmType](mods: Modifier[ReactiveSvgElement[T]]*): AmendedSvgTag[T] =
    new AmendedSvgTag[T](tag.name, tag.void, mods)

}
