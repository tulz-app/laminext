package io.laminext.syntax

import io.laminext.ops.future.FutureOfEitherOps

import scala.concurrent.Future

trait FutureOfEitherSyntax {

  implicit def syntaxFutureOfEither[A, B](f: => Future[Either[A, B]]): FutureOfEitherOps[A, B] = new FutureOfEitherOps[A, B](f)

}
