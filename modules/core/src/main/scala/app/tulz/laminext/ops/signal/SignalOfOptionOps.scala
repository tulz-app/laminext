package app.tulz.laminext.ops.signal

import app.tulz.laminext.ConditionalChildInserter
import com.raquo.airstream.signal.Signal
import com.raquo.domtypes.generic.Modifier
import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveElement

final class SignalOfOptionOps[A](underlying: Signal[Option[A]]) {

  @inline def isDefined: Signal[Boolean] = underlying.map(_.isDefined)

  @inline def isEmpty: Signal[Boolean] = underlying.map(_.isEmpty)

  @inline def childIfEmpty(child: => Child): Inserter[ReactiveElement.Base] =
    ConditionalChildInserter(underlying.map(_.isEmpty), child)

  @inline def childIfDefined(child: => Child): Inserter[ReactiveElement.Base] =
    ConditionalChildInserter(underlying.map(_.isDefined), child)

  @inline def optionMap[B](project: A => B): Signal[Option[B]] = underlying.map(_.map(project))

  def optionFlatMap[B](project: A => Option[B]): Signal[Option[B]] = underlying.map(_.flatMap(project))

  def withDefault(default: => A): Signal[A] = underlying.map(_.getOrElse(default))

}
