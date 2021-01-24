package io.laminext.core
package ops.future

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

final class FutureOfEitherOps[A, B](underlying: Future[Either[A, B]]) {

  @inline def isLeft: Future[Boolean] = underlying.map(_.isLeft)

  @inline def isRight: Future[Boolean] = underlying.map(_.isRight)

  @inline def rightMap[C](project: B => C): Future[Either[A, C]] = underlying.map(_.map(project))

  @inline def leftMap[C](project: A => C): Future[Either[C, B]] = underlying.map(_.left.map(project))

  @inline def eitherFold[C](fa: A => C, fb: B => C): Future[C] = underlying.map(_.fold(fa, fb))

  @inline def eitherSwap: Future[Either[B, A]] = underlying.map(_.swap)

}
