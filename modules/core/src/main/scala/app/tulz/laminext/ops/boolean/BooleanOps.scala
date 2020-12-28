package app.tulz.laminext.ops.boolean

import com.raquo.laminar.api.L._

final class BooleanOps(b: Boolean) {

  @inline def whenTrue[El <: Element](mods: Modifier[El]*): Modifier[El] =
    if (b) { mods }
    else { emptyMod }

  @inline def whenFalse[El <: Element](mods: Modifier[El]*): Modifier[El] =
    if (!b) { mods }
    else { emptyMod }

}
