package io.laminext

package object validation {

  type ValidatedValue[Err, A]  = Either[Err, A]
  type Validation[A, Err, Out] = A => ValidatedValue[Err, Out]

}
