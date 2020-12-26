package app.tulz.laminext.ops.signal

import com.raquo.airstream.signal.Signal
import com.raquo.domtypes.generic.Modifier
import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveElement

final class SignalOfOptionOps[A](underlying: Signal[Option[A]]) {

  @inline def isDefined: Signal[Boolean] = underlying.map(_.isDefined)

  @inline def isEmpty: Signal[Boolean] = underlying.map(_.isEmpty)

  @inline def renderIfEmpty[El <: Element](m: => ReactiveElement.Base): Modifier[El] =
    child.maybe <-- underlying.map {
      case Some(_) => None
      case None    => Some(m)
    }

  @inline def renderIfDefined[El <: Element](m: A => ReactiveElement.Base): Modifier[El] =
    child.maybe <-- underlying.map {
      case Some(a) => Some(m(a))
      case None    => None
    }

  def mapOption[B](project: A => B): Signal[Option[B]] =
    underlying.map(_.map(project))

  def flatMapOption[B](project: A => Option[B]): Signal[Option[B]] =
    underlying.map(_.flatMap(project))

  def withDefault(default: => A): Signal[A] =
    underlying.map(_.getOrElse(default))

}
