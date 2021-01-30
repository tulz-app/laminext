package io.laminext.validation

import cats.kernel.Semigroup
import io.laminext.validation.ops.ValidationCatsOps

trait ValidationCatsSyntax {

  implicit def syntaxValidationCats[A, Err: Semigroup](v: Validation[A, Err, A]): ValidationCatsOps[A, Err] =
    new ValidationCatsOps[A, Err](v)

}
