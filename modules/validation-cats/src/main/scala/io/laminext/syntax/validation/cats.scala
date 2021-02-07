package io.laminext.syntax.validation

import io.laminext.validation.ValidationCatsSyntax
import io.laminext.validation.ValidationSyntax

object cats extends ValidationSyntax with ValidationCatsSyntax {

  type ValidatedValue[Err, A]  = io.laminext.validation.ValidatedValue[Err, A]
  type Validation[A, Err, Out] = io.laminext.validation.Validation[A, Err, Out]

}
