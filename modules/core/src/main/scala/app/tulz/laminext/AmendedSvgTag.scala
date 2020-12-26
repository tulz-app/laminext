package app.tulz.laminext

import com.raquo.domtypes.generic.Modifier
import com.raquo.laminar.api.L._
import com.raquo.laminar.builders.SvgTag
import com.raquo.laminar.nodes.ReactiveSvgElement
import org.scalajs.dom

class AmendedSvgTag[+R <: dom.svg.Element](name: String, void: Boolean = false, amendments: Seq[Mod[ReactiveSvgElement[R]]]) extends SvgTag[R](name, void) {

  override def apply(modifiers: Modifier[ReactiveSvgElement[R]]*): ReactiveSvgElement[R] =
    super.apply(modifiers ++ amendments: _*)

  def amend(mods: Mod[ReactiveSvgElement[R]]*): AmendedSvgTag[R] =
    new AmendedSvgTag[R](name, void, amendments ++ mods)

}
