package io.laminext.core

import com.raquo.laminar.api.L._

class SignalOptionT[A](val value: Signal[Option[A]]) extends AnyVal {

  def fold[B](default: => B)(f: A => B): Signal[B] =
    value.map(_.fold(default)(f))

  /**
   * Transform this `SignalOptionT[A]` into a `Signal[B]`.
   */
  def foldF[B](default: => Signal[B])(f: A => Signal[B]): Signal[B] =
    value.flatMap(_.fold(default)(f))

  /**
   * Catamorphism on the Option. This is identical to [[fold]], but it only has one parameter list, which can result in better type inference in some contexts.
   */
  def cata[B](default: => B, f: A => B): Signal[B] =
    fold(default)(f)

  /**
   * Effectful catamorphism on the Option. This is identical to [[foldF]], but it only has one parameter list, which can result in better type inference in some
   * contexts.
   */
  def cataF[B](default: => Signal[B], f: A => Signal[B]): Signal[B] =
    foldF(default)(f)

  def map[B](f: A => B): SignalOptionT[B] =
    new SignalOptionT(value.map(_.map(f)))

  def mapFilter[B](f: A => Option[B]): SignalOptionT[B] =
    subflatMap(f)

  def flatMap[B](f: A => SignalOptionT[B]): SignalOptionT[B] =
    flatMapF(a => f(a).value)

  def flatMapF[B](f: A => Signal[Option[B]]): SignalOptionT[B] =
    new SignalOptionT(value.flatMap(_.fold[Signal[Option[B]]](Signal.fromValue[Option[B]](None))(f)))

  def flatTransform[B](f: Option[A] => Signal[Option[B]]): SignalOptionT[B] =
    new SignalOptionT(value.flatMap(f))

  def transform[B](f: Option[A] => Option[B]): SignalOptionT[B] =
    new SignalOptionT(value.map(f))

  def subflatMap[B](f: A => Option[B]): SignalOptionT[B] =
    transform(_.flatMap(f))

  def getOrElse[B >: A](default: => B): Signal[B] =
    value.map(_.getOrElse(default))

  def getOrElseF[B >: A](default: => Signal[B]): Signal[B] =
    value.flatMap(_.fold(default)(Signal.fromValue(_)))

  def collect[B](f: PartialFunction[A, B]): SignalOptionT[B] =
    new SignalOptionT(value.map(_.collect(f)))

  def exists(f: A => Boolean): Signal[Boolean] =
    value.map(_.exists(f))

  def filter(p: A => Boolean): SignalOptionT[A] =
    new SignalOptionT(value.map(_.filter(p)))

  def withFilter(p: A => Boolean): SignalOptionT[A] =
    filter(p)

  def filterNot(p: A => Boolean): SignalOptionT[A] =
    new SignalOptionT(value.map(_.filterNot(p)))

  def forall(f: A => Boolean): Signal[Boolean] =
    value.map(_.forall(f))

  def isDefined: Signal[Boolean] =
    value.map(_.isDefined)

  def isEmpty: Signal[Boolean] =
    value.map(_.isEmpty)

  def orElse(default: => SignalOptionT[A]): SignalOptionT[A] =
    orElseF(default.value)

  def orElseF(default: => Signal[Option[A]]): SignalOptionT[A] = {
    val s = value.flatMap {
      case s @ Some(_) => Signal.fromValue(s)
      case None        => default
    }
    new SignalOptionT(s)
  }

  def toRight[L](left: => L): SignalEitherT[L, A] =
    new SignalEitherT(cata(Left(left), Right.apply))

  def toRightSignal[L](left: => Signal[L]): SignalEitherT[L, A] =
    new SignalEitherT(cataF(left.map(Left.apply[L, A]), a => Signal.fromValue(Right(a))))

  def toLeft[R](right: => R): SignalEitherT[A, R] =
    new SignalEitherT(cata(Right(right), Left.apply))

  def toLeftSignal[R](right: => Signal[R]): SignalEitherT[A, R] =
    new SignalEitherT(cataF(right.map(Right.apply[A, R]), a => Signal.fromValue(Left(a))))

}
