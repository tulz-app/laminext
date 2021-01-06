package io.laminext.syntax

import com.raquo.laminar.api.L.Input
import io.laminext.ops.htmlelement.InputElementOps

trait InputElementSyntax {

  implicit def syntaxInputElement(el: Input): InputElementOps = new InputElementOps(el)

}
