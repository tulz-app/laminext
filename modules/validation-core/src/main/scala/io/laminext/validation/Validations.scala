package io.laminext.validation

import org.scalajs.dom.File

object Validations {

  def apply[A, Err](message: => Err)(check: A => Boolean): Validation[A, Err, A] =
    apply((a: A) => Option.when(!check(a))(message))

  def apply[A, Err](check: A => Option[Err]): Validation[A, Err, A] = s =>
    check(s).fold[ValidatedValue[Err, A]](
      Right[Err, A](s)
    ) { message =>
      Left[Err, A](message)
    }

  @inline def custom(message: => String)(check: String => Boolean): Validation[String, Seq[String], String] =
    apply[String, Seq[String]](Seq(message))(check)

  def custom(check: String => Option[String]): Validation[String, Seq[String], String] =
    apply[String, Seq[String]]((s: String) => check(s).map(Seq(_)))

  @inline def file[Err](error: => Err)(check: File => Boolean): Validation[File, Err, File] =
    apply[File, Err](error)(check)

  @inline def file[Err](check: File => Option[Err]): Validation[File, Err, File] =
    apply[File, Err](check)

  def nonBlank(message: String = "required"): Validation[String, Seq[String], String] =
    apply(Seq(message))(_.trim.nonEmpty)

  def email(message: String = "email required"): Validation[String, Seq[String], String] =
    apply(Seq(message))(EmailValidator.isValidEmail)

  def pass[T]: Validation[String, T, String] = s => Right(s)

  def isTrue(message: String): Validation[Boolean, Seq[String], Boolean] =
    apply(Seq(message))(identity)

  @inline def isFalse(message: String): Validation[Boolean, Seq[String], Boolean] = b => isTrue(message)(!b)

}
