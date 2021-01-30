package io.laminext.syntax.validation

import io.laminext.validation.ValidationCatsSyntax
import io.laminext.validation.Validations
import io.laminext.validation.ValidationSyntax

object cats extends ValidationSyntax with ValidationCatsSyntax {

  val V: Validations.type = Validations

}
