package io.laminext.validation

import cats.syntax.all._
import cats.data.NonEmptyChain

object Validations {

  def all(
    validations: String => Either[NonEmptyChain[String], String]*
  ): String => Either[NonEmptyChain[String], String] =
    s => validations.map(_(s)).parSequence.map(_ => s)

  @inline def custom(message: String)(check: String => Boolean): String => Either[NonEmptyChain[String], String] =
    s =>
      if (check(s)) { s.asRight }
      else { NonEmptyChain.one(message).asLeft }

  def nonBlank(message: String = "required"): String => Either[NonEmptyChain[String], String] =
    custom(message)(_.trim.nonEmpty)

  def email(message: String = "required"): String => Either[NonEmptyChain[String], String] =
    custom(message)(EmailValidator.isValidEmail)

  val pass: String => Either[NonEmptyChain[String], String] =
    custom("")(_ => true)

  def isTrue(message: String): Boolean => Either[NonEmptyChain[String], Boolean] =
    b =>
      if (b) { b.asRight }
      else { NonEmptyChain.one(message).asLeft }

  def isFalse(message: String): Boolean => Either[NonEmptyChain[String], Boolean] = b => isTrue(message)(!b)

}
