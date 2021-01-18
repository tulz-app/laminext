package io.laminext.ops.stream

import com.raquo.airstream.core.EventStream

final class EventStreamOfOptionOps[A](underlying: EventStream[Option[A]]) {

  @inline def collectDefined: EventStream[A] =
    underlying.collect { case Some(event) => event }

  @inline def optionCollect[B](pf: PartialFunction[A, B]): EventStream[B] =
    underlying.collect { case Some(event) if pf.isDefinedAt(event) => pf(event) }

  @inline def isDefined: EventStream[Boolean] =
    underlying.map(_.isDefined)

  @inline def isEmpty: EventStream[Boolean] =
    underlying.map(_.isEmpty)

  @inline def optionContains[B >: A](value: B): EventStream[Boolean] =
    underlying.map(_.contains(value))

  @inline def optionExists(predicate: A => Boolean): EventStream[Boolean] =
    underlying.map(_.exists(predicate))

  @inline def optionMap[B](project: A => B): EventStream[Option[B]] =
    underlying.map(_.map(project))

  @inline def optionFlatMap[B](project: A => Option[B]): EventStream[Option[B]] =
    underlying.map(_.flatMap(project))

  @inline def withDefault(default: => A): EventStream[A] =
    underlying.map(_.getOrElse(default))

  def someFlatMap[B](mapping: A => EventStream[B]): EventStream[Option[B]] = underlying.flatMap {
    case Some(a) => mapping(a).map(Some(_))
    case None    => EventStream.fromValue(Option.empty[B])
  }

}
