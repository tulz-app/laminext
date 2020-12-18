package app.tulz.laminext.ops.signal

import com.raquo.airstream.signal.Signal
import com.raquo.domtypes.generic.Modifier
import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveElement

final class SignalOfOptionOps[A](s: Signal[Option[A]]) {

  @inline def renderIfEmpty[El <: Element](m: => ReactiveElement.Base): Modifier[El] =
    child.maybe <-- s.map {
      case Some(_) => None
      case None    => Some(m)
    }

  @inline def renderIfDefined[El <: Element](m: A => ReactiveElement.Base): Modifier[El] =
    child.maybe <-- s.map {
      case Some(a) => Some(m(a))
      case None    => None
    }

  def mapOption[B](project: A => B): Signal[Option[B]] =
    s.map(_.map(project))

  def flatMapOption[B](project: A => Option[B]): Signal[Option[B]] =
    s.map(_.flatMap(project))

  def withDefault(default: => A): Signal[A] =
    s.map(_.getOrElse(default))

}
