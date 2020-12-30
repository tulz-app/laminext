package io.laminext.ops.option

import com.raquo.laminar.api.L._

final class OptionOps[A](o: Option[A]) {

  @inline def whenEmpty[El <: Element](mods: Modifier[El]*): Modifier[El] = {
    if (o.isEmpty) { mods }
    else { emptyMod }
  }

  @inline def whenDefined[El <: Element](mods: Modifier[El]*): Modifier[El] =
    if (o.isDefined) { mods }
    else { emptyMod }

}
