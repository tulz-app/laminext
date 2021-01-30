package io.laminext.validation
package ops

import cats.Monoid

final class ValidationOps[A, Err: Monoid](v: Validation[A, Err, A]) {

  def &&(other: Validation[A, Err, A]): Validation[A, Err, A] =
    ValidationsOps.all(v, other)

  def ||(other: Validation[A, Err, A]): Validation[A, Err, A] = (a: A) => {
    v(a) match {
      case Right(a) => Right(a)
      case Left(error) =>
        other(a) match {
          case Right(a)         => Right(a)
          case Left(otherError) => Left(Monoid.combine(error, otherError))
        }
    }
  }

}
