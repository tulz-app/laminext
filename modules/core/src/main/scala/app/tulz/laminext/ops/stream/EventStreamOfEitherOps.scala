package app.tulz.laminext.ops.stream

import com.raquo.airstream.eventstream.EventStream

final class EventStreamOfEitherOps[A, B](s: EventStream[Either[A, B]]) {

  def isRight: EventStream[Boolean] = s.map(_.isRight)

  def isLeft: EventStream[Boolean] = s.map(_.isLeft)

  def collectRights: EventStream[B] = s.collect { case Right(r) => r }

  def collectLefts: EventStream[A] = s.collect { case Left(l) => l }

  def rightCollect[C](f: PartialFunction[B, C]): EventStream[Either[A, C]] = s.collect {
    case Right(r) if f.isDefinedAt(r) => Right(f(r))
    case Left(l)                      => Left(l)
  }

  def leftCollect[C](f: PartialFunction[A, C]): EventStream[Either[C, B]] = s.collect {
    case Left(l) if f.isDefinedAt(l) => Left(f(l))
    case Right(r)                    => Right(r)
  }

  def rightFlatMap[C](mapping: B => EventStream[Either[A, C]]): EventStream[Either[A, C]] = s.flatMap {
    case Right(r) => mapping(r)
    case Left(l)  => EventStream.fromValue(Left(l), emitOnce = true)
  }

  def leftFlatMap[C](mapping: A => EventStream[Either[C, B]]): EventStream[Either[C, B]] = s.flatMap {
    case Right(r) => EventStream.fromValue(Right(r), emitOnce = true)
    case Left(l)  => mapping(l)
  }

  def rightMap[C](mapping: B => C): EventStream[Either[A, C]] = s.map {
    case Right(r) => Right(mapping(r))
    case Left(l)  => Left(l)
  }

  def leftMap[C](mapping: A => C): EventStream[Either[C, B]] = s.map {
    case Right(r) => Right(r)
    case Left(l)  => Left(mapping(l))
  }

}
