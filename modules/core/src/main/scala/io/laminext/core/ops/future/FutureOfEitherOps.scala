package io.laminext.core
package ops.future

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

final class FutureOfEitherOps[A, B](underlying: Future[Either[A, B]]) {

  @inline def isLeft(implicit ec: ExecutionContext): Future[Boolean] = underlying.map(_.isLeft)

  @inline def isRight(implicit ec: ExecutionContext): Future[Boolean] = underlying.map(_.isRight)

  @inline def rightMap[C](project: B => C)(implicit ec: ExecutionContext): Future[Either[A, C]] = underlying.map(_.map(project))

  @inline def leftMap[C](project: A => C)(implicit ec: ExecutionContext): Future[Either[C, B]] = underlying.map(_.left.map(project))

  @inline def eitherFold[C](fa: A => C, fb: B => C)(implicit ec: ExecutionContext): Future[C] = underlying.map(_.fold(fa, fb))

  @inline def eitherSwap(implicit ec: ExecutionContext): Future[Either[B, A]] = underlying.map(_.swap)

}
