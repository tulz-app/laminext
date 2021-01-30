package io.laminext.validation

object Validations {

  def apply[A, Err](message: => Err)(check: A => Boolean): Validation[A, Err, A] = s =>
    if (check(s)) {
      Right[Err, A](s)
    } else {
      Left[Err, A](message)
    }

  def custom(message: => String)(check: String => Boolean): Validation[String, Seq[String], String] =
    apply[String, Seq[String]](Seq(message))(check)

  def nonBlank(message: String = "required"): Validation[String, Seq[String], String] =
    apply(Seq(message))(_.trim.nonEmpty)

  def email(message: String = "required"): Validation[String, Seq[String], String] =
    apply(Seq(message))(EmailValidator.isValidEmail)

  def pass[T]: Validation[String, T, String] = s => Right(s)

  def isTrue(message: String): Validation[Boolean, Seq[String], Boolean] =
    apply(Seq(message))(identity)

  @inline def isFalse(message: String): Validation[Boolean, Seq[String], Boolean] = b => isTrue(message)(!b)

}
