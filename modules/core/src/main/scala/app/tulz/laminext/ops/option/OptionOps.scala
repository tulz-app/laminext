package app.tulz.laminext.ops.option

import app.tulz.laminext.ops.boolean.BooleanOps
import com.raquo.laminar.api.L._

final class OptionOps[A](o: Option[A]) {

  @inline def whenEmpty[El <: Element](mods: Modifier[El]*): Modifier[El] =
    new BooleanOps(o.isEmpty).whenTrue(mods)

  @inline def whenDefined[El <: Element](mods: Modifier[El]*): Modifier[El] =
    new BooleanOps(o.isDefined).whenTrue(mods)

}
