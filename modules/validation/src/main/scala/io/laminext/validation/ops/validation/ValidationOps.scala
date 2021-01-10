package io.laminext.validation
package ops.validation

final class ValidationOps[A](v: Validation[A]) {

  def &&(other: Validation[A]): Validation[A] =
    Validations.all(v, other)

  def ||(other: Validation[A]): Validation[A] = (a: A) => {
    v(a) match {
      case Right(a) => Right(a)
      case Left(errors) =>
        other(a) match {
          case Right(a)          => Right(a)
          case Left(otherErrors) => Left(errors ++ otherErrors)
        }
    }
  }

}
