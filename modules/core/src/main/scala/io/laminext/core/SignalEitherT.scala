package io.laminext.core

import com.raquo.laminar.api.L._

class SignalEitherT[A, B](val value: Signal[Either[A, B]]) {

  /**
   * Transform this `SignalEitherT[A, B]` into a `Signal[C]`.
   */
  def fold[C](fa: A => C, fb: B => C): Signal[C] = value.map(_.fold(fa, fb))

  /**
   * Transform this `SignalEitherT[A, B]` into a `Signal[C]`.
   */
  def foldF[C](fa: A => Signal[C], fb: B => Signal[C]): Signal[C] = value.flatMap(_.fold(fa, fb))

  def isLeft: Signal[Boolean] = value.map(_.isLeft)

  def isRight: Signal[Boolean] = value.map(_.isRight)

  def swap: SignalEitherT[B, A] = new SignalEitherT(value.map(_.swap))

  def getOrElse[BB >: B](default: => BB): Signal[BB] = value.map(_.getOrElse(default))

  def getOrElseF[BB >: B](default: => Signal[BB]): Signal[BB] =
    value.flatMap {
      case Left(_)  => default
      case Right(b) => Signal.fromValue(b)
    }

  def orElse[C, BB >: B](default: => SignalEitherT[C, BB]): SignalEitherT[C, BB] =
    new SignalEitherT(value.flatMap {
      case Left(_)  => default.value
      case Right(b) => Signal.fromValue(Right(b))
    })

  def recover(pf: PartialFunction[A, B]): SignalEitherT[A, B] =
    new SignalEitherT(
      value.map {
        case Left(a) if pf.isDefinedAt(a) => Right(pf(a))
        case eab                          => eab
      }
    )

  def recoverWith(pf: PartialFunction[A, SignalEitherT[A, B]]): SignalEitherT[A, B] = {
    val s = value.flatMap {
      case Left(a) if pf.isDefinedAt(a) => pf(a).value
      case other                        => Signal.fromValue(other)
    }
    new SignalEitherT(s)
  }

  def valueOr[BB >: B](f: A => BB): Signal[BB] = fold(f, identity)

  def valueOrF[BB >: B](f: A => Signal[BB]): Signal[BB] =
    value.flatMap {
      case Left(a)  => f(a)
      case Right(b) => Signal.fromValue(b)
    }

  def forall(f: B => Boolean): Signal[Boolean] = value.map(_.forall(f))

  def exists(f: B => Boolean): Signal[Boolean] = value.map(_.exists(f))

  def ensure[AA >: A](onFailure: => AA)(f: B => Boolean): SignalEitherT[AA, B] =
    new SignalEitherT(
      value.map {
        case eab @ Left(_)  => eab
        case eab @ Right(b) => if (f(b)) eab else Left(onFailure)
      }
    )

  def ensureOr[AA >: A](onFailure: B => AA)(f: B => Boolean): SignalEitherT[AA, B] =
    new SignalEitherT(
      value.map {
        case eab @ Left(_)  => eab
        case eab @ Right(b) => if (f(b)) eab else Left(onFailure(b))
      }
    )

  def toOption: SignalOptionT[B] = new SignalOptionT(value.map(_.toOption))

  def bimap[C, D](fa: A => C, fb: B => D): SignalEitherT[C, D] =
    new SignalEitherT(
      value.map {
        case Right(b) => Right(fb(b))
        case Left(a)  => Left(fa(a))
      }
    )

  def biflatMap[C, D](fa: A => SignalEitherT[C, D], fb: B => SignalEitherT[C, D]): SignalEitherT[C, D] =
    new SignalEitherT(value.flatMap {
      case Left(a)  => fa(a).value
      case Right(a) => fb(a).value
    })

  def flatMap[AA >: A, D](f: B => SignalEitherT[AA, D]): SignalEitherT[AA, D] =
    new SignalEitherT(value.flatMap {
      case Left(a)  => Signal.fromValue(Left(a))
      case Right(b) => f(b).value
    })

  def flatMapF[AA >: A, D](f: B => Signal[Either[AA, D]]): SignalEitherT[AA, D] =
    flatMap(b => new SignalEitherT(f(b)))

  def transform[C, D](f: Either[A, B] => Either[C, D]): SignalEitherT[C, D] =
    new SignalEitherT(value.map(f))

  def subflatMap[AA >: A, D](f: B => Either[AA, D]): SignalEitherT[AA, D] =
    transform(_.flatMap(f))

  def map[D](f: B => D): SignalEitherT[A, D] = bimap(identity, f)

  def leftMap[C](f: A => C): SignalEitherT[C, B] = bimap(f, identity)

  def leftFlatMap[BB >: B, C](f: A => SignalEitherT[C, BB]): SignalEitherT[C, BB] =
    new SignalEitherT(value.flatMap {
      case Left(a)  => f(a).value
      case Right(b) => Signal.fromValue(Right(b))
    })

}
