package io.laminext.syntax

import com.raquo.laminar.api.L._
import io.laminext.core.ops.signal.VarOfBooleanOps

trait VarOfBooleanSyntax {

  implicit def syntaxVarOfBoolean(v: Var[Boolean]): VarOfBooleanOps = new VarOfBooleanOps(v)

}
