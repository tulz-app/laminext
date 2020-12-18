package app.tulz.validation

import cats.syntax.all._
import cats.data.NonEmptyChain

object Validations {

  def all(validations: Validation[String]*): Validation[String] =
    s => validations.map(_(s)).parSequence.map(_ => s)

  @inline def custom(message: String)(check: String => Boolean): Validation[String] =
    s =>
      if (check(s)) { s.asRight }
      else { NonEmptyChain.one(message).asLeft }

  def nonBlank(message: String = "required"): Validation[String] =
    custom(message)(!_.isBlank)

  def email(message: String = "required"): Validation[String] =
    custom(message)(EmailValidator.isValidEmail)

  def pass: Validation[String] =
    custom("")(_ => true)

  def isTrue(message: String = "required"): Validation[Boolean] =
    b =>
      if (b) { b.asRight }
      else { NonEmptyChain.one(message).asLeft }

}
