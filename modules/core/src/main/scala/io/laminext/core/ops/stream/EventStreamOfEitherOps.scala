package io.laminext.core
package ops.stream

import com.raquo.laminar.api.L._

final class EventStreamOfEitherOps[L, R](underlying: EventStream[Either[L, R]]) {

  @inline def eitherT: EventStreamEitherT[L, R] = new EventStreamEitherT(underlying)

  def isRight: EventStream[Boolean] = underlying.map(_.isRight)

  def isLeft: EventStream[Boolean] = underlying.map(_.isLeft)

  def collectRight: EventStream[R] = underlying.collect { case Right(r) => r }

  def collectLeft: EventStream[L] = underlying.collect { case Left(l) => l }

  def rightMap[C](mapping: R => C): EventStream[Either[L, C]] = underlying.map {
    case Right(r) => Right(mapping(r))
    case Left(l)  => Left(l)
  }

  def leftMap[C](mapping: L => C): EventStream[Either[C, R]] = underlying.map {
    case Right(r) => Right(r)
    case Left(l)  => Left(mapping(l))
  }

}
