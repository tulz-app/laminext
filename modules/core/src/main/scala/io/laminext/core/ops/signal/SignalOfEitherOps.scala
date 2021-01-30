package io.laminext.core
package ops.signal

import com.raquo.laminar.api.L._

final class SignalOfEitherOps[L, R](underlying: Signal[Either[L, R]]) {

  @inline def isLeft: Signal[Boolean] = underlying.map(_.isLeft)

  @inline def isRight: Signal[Boolean] = underlying.map(_.isRight)

  @inline def eitherRightMap[C](project: R => C): Signal[Either[L, C]] = underlying.map(_.map(project))

  @inline def eitherLeftMap[C](project: L => C): Signal[Either[C, R]] = underlying.map(_.left.map(project))

  @inline def eitherToOption: Signal[Option[R]] = underlying.map(_.toOption)

  @inline def eitherLeftToOption: Signal[Option[L]] = underlying.map(_.left.toOption)

  @inline def eitherFold[C](fa: L => C, fb: R => C): Signal[C] = underlying.map(_.fold(fa, fb))

  @inline def eitherSwap: Signal[Either[R, L]] = underlying.map(_.swap)

  def flatMapWhenRight[C](mapping: R => Signal[Either[L, C]]): Signal[Either[L, C]] = underlying.flatMap {
    case Right(r) => mapping(r)
    case Left(l)  => Val(Left(l))
  }

  def flatMapWhenLeft[C](mapping: L => Signal[Either[C, R]]): Signal[Either[C, R]] = underlying.flatMap {
    case Right(r) => Val(Right(r))
    case Left(l)  => mapping(l)
  }

}
