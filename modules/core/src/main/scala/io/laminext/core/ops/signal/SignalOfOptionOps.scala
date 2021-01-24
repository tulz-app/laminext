package io.laminext.core
package ops.signal

import com.raquo.laminar.api.L._

final class SignalOfOptionOps[A](underlying: Signal[Option[A]]) {

  @inline def isDefined: Signal[Boolean] = underlying.map(_.isDefined)

  @inline def isEmpty: Signal[Boolean] = underlying.map(_.isEmpty)

  @inline def optionContains[B >: A](value: B): Signal[Boolean] =
    underlying.map(_.contains(value))

  @inline def optionExists(predicate: A => Boolean): Signal[Boolean] =
    underlying.map(_.exists(predicate))

  @inline def optionMap[B](project: A => B): Signal[Option[B]] =
    underlying.map(_.map(project))

  @inline def optionFlatMap[B](project: A => Option[B]): Signal[Option[B]] =
    underlying.map(_.flatMap(project))

  @inline def withDefault(default: => A): Signal[A] =
    underlying.map(_.getOrElse(default))

  def someFlatMap[B](mapping: A => Signal[B]): Signal[Option[B]] = underlying.flatMap {
    case Some(a) => mapping(a).map(Some(_))
    case None    => Val(Option.empty[B])
  }

}
