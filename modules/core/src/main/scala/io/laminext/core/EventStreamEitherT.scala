package io.laminext.core

import com.raquo.laminar.api.L._

class EventStreamEitherT[A, B](val value: EventStream[Either[A, B]]) {

  /**
   * Transform this `EventStreamEitherT[A, B]` into a `EventStream[C]`.
   */
  def fold[C](fa: A => C, fb: B => C): EventStream[C] = value.map(_.fold(fa, fb))

  /**
   * Transform this `EventStreamEitherT[A, B]` into a `EventStream[C]`.
   */
  def foldF[C](fa: A => EventStream[C], fb: B => EventStream[C]): EventStream[C] = value.flatMap(_.fold(fa, fb))

  def isLeft: EventStream[Boolean] = value.map(_.isLeft)

  def isRight: EventStream[Boolean] = value.map(_.isRight)

  def swap: EventStreamEitherT[B, A] = new EventStreamEitherT(value.map(_.swap))

  def getOrElse[BB >: B](default: => BB): EventStream[BB] = value.map(_.getOrElse(default))

  def getOrElseF[BB >: B](default: => EventStream[BB]): EventStream[BB] =
    value.flatMap {
      case Left(_)  => default
      case Right(b) => EventStream.fromValue(b)
    }

  def orElse[C, BB >: B](default: => EventStreamEitherT[C, BB]): EventStreamEitherT[C, BB] =
    new EventStreamEitherT(value.flatMap {
      case Left(_)  => default.value
      case Right(b) => EventStream.fromValue(Right(b))
    })

  def recover(pf: PartialFunction[A, B]): EventStreamEitherT[A, B] =
    new EventStreamEitherT(
      value.map {
        case Left(a) if pf.isDefinedAt(a) => Right(pf(a))
        case eab                          => eab
      }
    )

  def recoverWith(pf: PartialFunction[A, EventStreamEitherT[A, B]]): EventStreamEitherT[A, B] = {
    val s = value.flatMap {
      case Left(a) if pf.isDefinedAt(a) => pf(a).value
      case other                        => EventStream.fromValue(other)
    }
    new EventStreamEitherT(s)
  }

  def valueOr[BB >: B](f: A => BB): EventStream[BB] = fold(f, identity)

  def valueOrF[BB >: B](f: A => EventStream[BB]): EventStream[BB] =
    value.flatMap {
      case Left(a)  => f(a)
      case Right(b) => EventStream.fromValue(b)
    }

  def forall(f: B => Boolean): EventStream[Boolean] = value.map(_.forall(f))

  def exists(f: B => Boolean): EventStream[Boolean] = value.map(_.exists(f))

  def ensure[AA >: A](onFailure: => AA)(f: B => Boolean): EventStreamEitherT[AA, B] =
    new EventStreamEitherT(
      value.map {
        case eab @ Left(_)  => eab
        case eab @ Right(b) => if (f(b)) eab else Left(onFailure)
      }
    )

  def ensureOr[AA >: A](onFailure: B => AA)(f: B => Boolean): EventStreamEitherT[AA, B] =
    new EventStreamEitherT(
      value.map {
        case eab @ Left(_)  => eab
        case eab @ Right(b) => if (f(b)) eab else Left(onFailure(b))
      }
    )

  def toOption: EventStreamOptionT[B] = new EventStreamOptionT(value.map(_.toOption))

  def collectRight: EventStream[B] =
    value.collect { case Right(b) => b }

  def collectLeft: EventStream[A] =
    value.collect { case Left(a) => a }

  def bimap[C, D](fa: A => C, fb: B => D): EventStreamEitherT[C, D] =
    new EventStreamEitherT(
      value.map {
        case Right(b) => Right(fb(b))
        case Left(a)  => Left(fa(a))
      }
    )

  def biflatMap[C, D](fa: A => EventStreamEitherT[C, D], fb: B => EventStreamEitherT[C, D]): EventStreamEitherT[C, D] =
    new EventStreamEitherT(value.flatMap {
      case Left(a)  => fa(a).value
      case Right(a) => fb(a).value
    })

  def flatMap[AA >: A, D](f: B => EventStreamEitherT[AA, D]): EventStreamEitherT[AA, D] =
    new EventStreamEitherT(value.flatMap {
      case Left(a)  => EventStream.fromValue(Left(a))
      case Right(b) => f(b).value
    })

  def flatMapF[AA >: A, D](f: B => EventStream[Either[AA, D]]): EventStreamEitherT[AA, D] =
    flatMap(b => new EventStreamEitherT(f(b)))

  def transform[C, D](f: Either[A, B] => Either[C, D]): EventStreamEitherT[C, D] =
    new EventStreamEitherT(value.map(f))

  def subflatMap[AA >: A, D](f: B => Either[AA, D]): EventStreamEitherT[AA, D] =
    transform(_.flatMap(f))

  def map[D](f: B => D): EventStreamEitherT[A, D] = bimap(identity, f)

  def leftMap[C](f: A => C): EventStreamEitherT[C, B] = bimap(f, identity)

  def leftFlatMap[BB >: B, C](f: A => EventStreamEitherT[C, BB]): EventStreamEitherT[C, BB] =
    new EventStreamEitherT(value.flatMap {
      case Left(a)  => f(a).value
      case Right(b) => EventStream.fromValue(Right(b))
    })

}
