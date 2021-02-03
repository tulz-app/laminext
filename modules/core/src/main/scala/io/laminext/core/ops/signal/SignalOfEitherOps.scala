package io.laminext.core
package ops.signal

import com.raquo.laminar.api.L._

final class SignalOfEitherOps[L, R](underlying: Signal[Either[L, R]]) {

  @inline def eitherT: SignalEitherT[L, R] = new SignalEitherT(underlying)

  @inline def isLeft: Signal[Boolean] = underlying.map(_.isLeft)

  @inline def isRight: Signal[Boolean] = underlying.map(_.isRight)

  @inline def rightMap[C](project: R => C): Signal[Either[L, C]] = underlying.map(_.map(project))

  @inline def leftMap[C](project: L => C): Signal[Either[C, R]] = underlying.map(_.left.map(project))

}
