package io.laminext.ops.stream

import com.raquo.airstream.core.EventStream

final class EventStreamOfEitherOps[A, B](underlying: EventStream[Either[A, B]]) {

  def isRight: EventStream[Boolean] = underlying.map(_.isRight)

  def isLeft: EventStream[Boolean] = underlying.map(_.isLeft)

  def collectRights: EventStream[B] = underlying.collect { case Right(r) => r }

  def collectLefts: EventStream[A] = underlying.collect { case Left(l) => l }

  def rightMap[C](mapping: B => C): EventStream[Either[A, C]] = underlying.map {
    case Right(r) => Right(mapping(r))
    case Left(l)  => Left(l)
  }

  def leftMap[C](mapping: A => C): EventStream[Either[C, B]] = underlying.map {
    case Right(r) => Right(r)
    case Left(l)  => Left(mapping(l))
  }

  def rightMapC[C](f: PartialFunction[B, C]): EventStream[Either[A, C]] = underlying.collect {
    case Right(r) if f.isDefinedAt(r) => Right(f(r))
    case Left(l)                      => Left(l)
  }

  def leftMapC[C](f: PartialFunction[A, C]): EventStream[Either[C, B]] = underlying.collect {
    case Left(l) if f.isDefinedAt(l) => Left(f(l))
    case Right(r)                    => Right(r)
  }

  def rightFlatMap[C](mapping: B => EventStream[Either[A, C]]): EventStream[Either[A, C]] = underlying.flatMap {
    case Right(r) => mapping(r)
    case Left(l)  => EventStream.fromValue(Left(l), emitOnce = true)
  }

  def leftFlatMap[C](mapping: A => EventStream[Either[C, B]]): EventStream[Either[C, B]] = underlying.flatMap {
    case Right(r) => EventStream.fromValue(Right(r), emitOnce = true)
    case Left(l)  => mapping(l)
  }

  def eitherFold[C](fa: A => C, fb: B => C): EventStream[C] = underlying.map(_.fold(fa, fb))

  def eitherSwap: EventStream[Either[B, A]] = underlying.map(_.swap)

}
