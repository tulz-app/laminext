package io.laminext.syntax

import com.raquo.airstream.signal.Var
import io.laminext.ops.signal.VarOfBooleanOps

trait VarOfBooleanSyntax {

  implicit def syntaxVarOfBoolean(v: Var[Boolean]): VarOfBooleanOps = new VarOfBooleanOps(v)

}
