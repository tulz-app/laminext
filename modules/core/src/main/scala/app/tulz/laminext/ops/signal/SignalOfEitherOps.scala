package app.tulz.laminext.ops.signal

import com.raquo.airstream.signal.Signal
import com.raquo.airstream.signal.Val

final class SignalOfEitherOps[A, B](underlying: Signal[Either[A, B]]) {

  @inline def isLeft: Signal[Boolean] = underlying.map(_.isLeft)

  @inline def isRight: Signal[Boolean] = underlying.map(_.isRight)

  def rightFlatMap[C](mapping: B => Signal[Either[A, C]]): Signal[Either[A, C]] = underlying.flatMap {
    case Right(r) => mapping(r)
    case Left(l)  => Val(Left(l))
  }

  def leftFlatMap[C](mapping: A => Signal[Either[C, B]]): Signal[Either[C, B]] = underlying.flatMap {
    case Right(r) => Val(Right(r))
    case Left(l)  => mapping(l)
  }

  @inline def rightMap[C](project: B => C): Signal[Either[A, C]] = underlying.map(_.map(project))

  @inline def leftMap[C](project: A => C): Signal[Either[C, B]] = underlying.map(_.left.map(project))

  @inline def eitherToOption: Signal[Option[B]] = underlying.map(_.toOption)

  @inline def eitherFold[C](fa: A => C, fb: B => C): Signal[C] = underlying.map(_.fold(fa, fb))

  @inline def eitherSwap: Signal[Either[B, A]] = underlying.map(_.swap)

}
