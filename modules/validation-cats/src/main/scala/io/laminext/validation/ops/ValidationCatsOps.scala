package io.laminext.validation
package ops

import cats.kernel.Semigroup

final class ValidationCatsOps[A, Err: Semigroup](underlying: Validation[A, Err, A]) {

  def &(other: Validation[A, Err, A]): Validation[A, Err, A] = (a: A) =>
    underlying(a) match {
      case Right(a) =>
        other(a) match {
          case Right(a)         => Right(a)
          case Left(otherError) => Left(otherError)
        }
      case Left(error) =>
        other(a) match {
          case Right(_)         => Left(error)
          case Left(otherError) => Left(Semigroup.combine(error, otherError))
        }
    }

  def |(other: Validation[A, Err, A]): Validation[A, Err, A] = (a: A) =>
    underlying(a) match {
      case Right(a) => Right(a)
      case Left(error) =>
        other(a) match {
          case Right(a)         => Right(a)
          case Left(otherError) => Left(Semigroup.combine(error, otherError))
        }
    }

}
