package app.tulz.laminext.ops.boolean

import com.raquo.laminar.api.L._
import app.tulz.laminar.ext.noop
import com.raquo.domtypes.generic.Modifier

class BooleanOps(b: Boolean) {

  @inline def renderIfTrue[El <: Element](m: Modifier[El]*): Modifier[El] =
    if (b) {
      m
    } else {
      noop
    }

  @inline def renderIfFalse[El <: Element](m: => Modifier[El]): Modifier[El] =
    if (!b) {
      m
    } else {
      noop
    }

}
