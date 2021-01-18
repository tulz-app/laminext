package io.laminext.syntax

import com.raquo.airstream.state.Var
import io.laminext.ops.signal.VarOfBooleanOps

trait VarOfBooleanSyntax {

  implicit def syntaxVarOfBoolean(v: Var[Boolean]): VarOfBooleanOps = new VarOfBooleanOps(v)

}
