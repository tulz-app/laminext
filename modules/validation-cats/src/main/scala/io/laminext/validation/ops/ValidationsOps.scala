package io.laminext.validation.ops

import cats.Monoid
import cats.syntax.all._
import io.laminext.validation.Validation

object ValidationsOps {

  def all[A, Err: Monoid](
    validations: Validation[A, Err, A]*
  ): Validation[A, Err, A] =
    s => {
      validations.map(_(s)).parSequence.map(_ => s)
    }

}
