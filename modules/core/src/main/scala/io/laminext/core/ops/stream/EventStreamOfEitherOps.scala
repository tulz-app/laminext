package io.laminext.core
package ops.stream

import com.raquo.laminar.api.L._

final class EventStreamOfEitherOps[L, R](underlying: EventStream[Either[L, R]]) {

  def isRight: EventStream[Boolean] = underlying.map(_.isRight)

  def isLeft: EventStream[Boolean] = underlying.map(_.isLeft)

  def collectRights: EventStream[R] = underlying.collect { case Right(r) => r }

  def collectLefts: EventStream[L] = underlying.collect { case Left(l) => l }

  def rightMap[C](mapping: R => C): EventStream[Either[L, C]] = underlying.map {
    case Right(r) => Right(mapping(r))
    case Left(l)  => Left(l)
  }

  def leftMap[C](mapping: L => C): EventStream[Either[C, R]] = underlying.map {
    case Right(r) => Right(r)
    case Left(l)  => Left(mapping(l))
  }

  def rightMapC[C](f: PartialFunction[R, C]): EventStream[Either[L, C]] = underlying.collect {
    case Right(r) if f.isDefinedAt(r) => Right(f(r))
    case Left(l)                      => Left(l)
  }

  def leftMapC[C](f: PartialFunction[L, C]): EventStream[Either[C, R]] = underlying.collect {
    case Left(l) if f.isDefinedAt(l) => Left(f(l))
    case Right(r)                    => Right(r)
  }

  def flatMapWhenRight[C](mapping: R => EventStream[Either[L, C]]): EventStream[Either[L, C]] = underlying.flatMap {
    case Right(r) => mapping(r)
    case Left(l)  => EventStream.fromValue(Left(l))
  }

  def flatMapWhenLeft[C](mapping: L => EventStream[Either[C, R]]): EventStream[Either[C, R]] = underlying.flatMap {
    case Right(r) => EventStream.fromValue(Right(r))
    case Left(l)  => mapping(l)
  }

  def eitherFold[C](fa: L => C, fb: R => C): EventStream[C] = underlying.map(_.fold(fa, fb))

  def eitherSwap: EventStream[Either[R, L]] = underlying.map(_.swap)

}
