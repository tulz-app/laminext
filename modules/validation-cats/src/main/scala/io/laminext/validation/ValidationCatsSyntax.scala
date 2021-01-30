package io.laminext.validation

import cats.Monoid
import io.laminext.validation.ops.ValidationOps
import io.laminext.validation.ops.ValidationsOps

trait ValidationCatsSyntax {

  implicit def syntaxValidation[A, Err: Monoid](v: Validation[A, Err, A]): ValidationOps[A, Err] =
    new ValidationOps[A, Err](v)

  implicit def syntaxValidations[A, Err: Monoid](v: Validations.type): ValidationsOps.type = ValidationsOps

}
