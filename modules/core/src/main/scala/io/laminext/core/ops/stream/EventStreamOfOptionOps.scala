package io.laminext.core
package ops.stream

import com.raquo.laminar.api.L._

final class EventStreamOfOptionOps[A](underlying: EventStream[Option[A]]) {

  @inline def optionT: EventStreamOptionT[A] = new EventStreamOptionT(underlying)

  @inline def collectDefined: EventStream[A]                                    =
    underlying.collect { case Some(event) => event }

  @inline def isDefined: EventStream[Boolean]                                   =
    underlying.map(_.isDefined)

  @inline def isEmpty: EventStream[Boolean]                                     =
    underlying.map(_.isEmpty)

  @inline def optionContains[B >: A](value: B): EventStream[Boolean]            =
    underlying.map(_.contains(value))

  @inline def optionExists(predicate: A => Boolean): EventStream[Boolean]       =
    underlying.map(_.exists(predicate))

  @inline def optionMap[B](project: A => B): EventStream[Option[B]]             =
    underlying.map(_.map(project))

  @inline def optionFlatMap[B](project: A => Option[B]): EventStream[Option[B]] =
    underlying.map(_.flatMap(project))

  @inline def withDefault(default: => A): EventStream[A]                        =
    underlying.map(_.getOrElse(default))

}
