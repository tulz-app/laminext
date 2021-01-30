package io.laminext.validation
package ops

final class ValidationOps[A, Err](underlying: Validation[A, Err, A]) {

  def &&(other: Validation[A, Err, A]): Validation[A, Err, A] = (a: A) =>
    underlying(a) match {
      case Right(a)    => other(a)
      case Left(error) => Left(error)
    }

}
