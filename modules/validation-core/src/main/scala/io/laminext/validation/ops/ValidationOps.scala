package io.laminext.validation
package ops

final class ValidationOps[A, Err, Out](underlying: Validation[A, Err, Out]) {

  def &&(other: Validation[A, Err, Out]): Validation[A, Err, Out] = (a: A) =>
    underlying(a) match {
      case Right(_)    => other(a)
      case Left(error) => Left(error)
    }

  def flatMap[Out2](nextValidation: Validation[Out, Err, Out2]): Validation[A, Err, Out2] =
    a =>
      underlying(a) match {
        case Right(out) => nextValidation(out)
        case Left(err)  => Left(err)
      }

}
