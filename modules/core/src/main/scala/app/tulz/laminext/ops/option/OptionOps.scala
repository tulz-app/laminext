package app.tulz.laminext.ops.option

import app.tulz.laminext.ops.boolean.BooleanOps
import com.raquo.laminar.api.L._
import com.raquo.domtypes.generic.Modifier

class OptionOps[A](o: Option[A]) {

  @inline def renderIfEmpty[El <: Element](m: => Modifier[El]): Modifier[El] =
    new BooleanOps(o.isEmpty).renderIfTrue(m)

  @inline def renderIfDefined[El <: Element](m: => Modifier[El]): Modifier[El] =
    new BooleanOps(o.isDefined).renderIfTrue(m)

}
