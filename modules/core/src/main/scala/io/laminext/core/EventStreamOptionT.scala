package io.laminext.core

import com.raquo.laminar.api.L._

class EventStreamOptionT[A](val value: EventStream[Option[A]]) extends AnyVal {

  def fold[B](default: => B)(f: A => B): EventStream[B] =
    value.map(_.fold(default)(f))

  /**
   * Transform this `EventStreamOptionT[A]` into a `EventStream[B]`.
   */
  def foldF[B](default: => EventStream[B])(f: A => EventStream[B]): EventStream[B] =
    value.flatMap(_.fold(default)(f))

  /**
   * Catamorphism on the Option. This is identical to [[fold]], but it only has one parameter list, which can result in better type inference in some contexts.
   */
  def cata[B](default: => B, f: A => B): EventStream[B] =
    fold(default)(f)

  /**
   * Effectful catamorphism on the Option. This is identical to [[foldF]], but it only has one parameter list, which can result in better type inference in some
   * contexts.
   */
  def cataF[B](default: => EventStream[B], f: A => EventStream[B]): EventStream[B] =
    foldF(default)(f)

  def map[B](f: A => B): EventStreamOptionT[B] =
    new EventStreamOptionT(value.map(_.map(f)))

  def mapFilter[B](f: A => Option[B]): EventStreamOptionT[B] =
    subflatMap(f)

  def flatMap[B](f: A => EventStreamOptionT[B]): EventStreamOptionT[B] =
    flatMapF(a => f(a).value)

  def flatMapF[B](f: A => EventStream[Option[B]]): EventStreamOptionT[B] =
    new EventStreamOptionT(value.flatMap(_.fold(EventStream.fromValue[Option[B]](None))(f)))

  def flatTransform[B](f: Option[A] => EventStream[Option[B]]): EventStreamOptionT[B] =
    new EventStreamOptionT(value.flatMap(f))

  def transform[B](f: Option[A] => Option[B]): EventStreamOptionT[B] =
    new EventStreamOptionT(value.map(f))

  def subflatMap[B](f: A => Option[B]): EventStreamOptionT[B] =
    transform(_.flatMap(f))

  def getOrElse[B >: A](default: => B): EventStream[B] =
    value.map(_.getOrElse(default))

  def getOrElseF[B >: A](default: => EventStream[B]): EventStream[B] =
    value.flatMap(_.fold(default)(EventStream.fromValue(_)))

  def collect[B](f: PartialFunction[A, B]): EventStreamOptionT[B] =
    new EventStreamOptionT(value.map(_.collect(f)))

  def exists(f: A => Boolean): EventStream[Boolean] =
    value.map(_.exists(f))

  def filter(p: A => Boolean): EventStreamOptionT[A] =
    new EventStreamOptionT(value.map(_.filter(p)))

  def withFilter(p: A => Boolean): EventStreamOptionT[A] =
    filter(p)

  def filterNot(p: A => Boolean): EventStreamOptionT[A] =
    new EventStreamOptionT(value.map(_.filterNot(p)))

  def forall(f: A => Boolean): EventStream[Boolean] =
    value.map(_.forall(f))

  def isDefined: EventStream[Boolean] =
    value.map(_.isDefined)

  def isEmpty: EventStream[Boolean] =
    value.map(_.isEmpty)

  def orElse(default: => EventStreamOptionT[A]): EventStreamOptionT[A] =
    orElseF(default.value)

  def orElseF(default: => EventStream[Option[A]]): EventStreamOptionT[A] = {
    val s = value.flatMap {
      case s @ Some(_) => EventStream.fromValue(s)
      case None        => default
    }
    new EventStreamOptionT(s)
  }

  def toRight[L](left: => L): EventStreamEitherT[L, A] =
    new EventStreamEitherT(cata(Left(left), Right.apply))

  def toRightF[L](left: => EventStream[L]): EventStreamEitherT[L, A] =
    new EventStreamEitherT(cataF(left.map(Left.apply[L, A]), a => EventStream.fromValue(Right(a))))

  def toLeft[R](right: => R): EventStreamEitherT[A, R] =
    new EventStreamEitherT(cata(Right(right), Left.apply))

  def toLeftF[R](right: => EventStream[R]): EventStreamEitherT[A, R] =
    new EventStreamEitherT(cataF(right.map(Right.apply[A, R]), a => EventStream.fromValue(Left(a))))

}
