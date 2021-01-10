package io.laminext.validation

object Validations {

  def all[A](
    validations: Validation[A]*
  ): Validation[A] =
    s => {
//      validations.map(_(s)).parSequence.map(_ => s)
      val allValidations = validations.map(_(s))
      val allErrors = allValidations.collect { case Left(error) =>
        error
      }
      if (allErrors.nonEmpty) {
        Left[ValidationError, A](allErrors.flatten)
      } else {
        Right(s)
      }
    }

  @inline def custom[A](message: String)(check: A => Boolean): Validation[A] =
    s =>
      if (check(s)) { Right(s) }
      else { Left(Seq(message)) }

  def nonBlank(message: String = "required"): Validation[String] =
    custom(message)(_.trim.nonEmpty)

  def email(message: String = "required"): Validation[String] =
    custom(message)(EmailValidator.isValidEmail)

  val pass: Validation[String] =
    custom("")(_ => true)

  def isTrue(message: String): Validation[Boolean] =
    custom[Boolean](message)(identity)

  @inline def isFalse(message: String): Validation[Boolean] = b => isTrue(message)(!b)

}
