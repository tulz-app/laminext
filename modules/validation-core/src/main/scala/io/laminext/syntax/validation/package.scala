package io.laminext.syntax

import io.laminext.validation.ValidationSyntax

package object validation extends ValidationSyntax {

  type ValidatedValue[Err, A]  = io.laminext.validation.ValidatedValue[Err, A]
  type Validation[A, Err, Out] = io.laminext.validation.Validation[A, Err, Out]

}
