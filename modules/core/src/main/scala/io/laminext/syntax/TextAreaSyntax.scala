package io.laminext.syntax

import com.raquo.laminar.api.L.TextArea
import io.laminext.core.ops.htmlelement.TextAreaElementOps

trait TextAreaSyntax {

  implicit def syntaxTextAreaElement(el: TextArea): TextAreaElementOps = new TextAreaElementOps(el)

}
