package app.tulz.laminext.ops.svgtag

import app.tulz.laminar.ext.AmAny
import app.tulz.laminar.ext.AmendedSvgTag
import com.raquo.domtypes.generic.Modifier
import com.raquo.laminar.builders.SvgTag
import com.raquo.laminar.nodes.ReactiveSvgElement
import org.scalajs.dom

class SvgTagOps[T <: dom.svg.Element](tag: SvgTag[T]) {

  @inline def amend[AmType <: AmAny](mods: Modifier[ReactiveSvgElement[T]]*): AmendedSvgTag[T] =
    new AmendedSvgTag[T](tag.name, tag.void, mods)

}
