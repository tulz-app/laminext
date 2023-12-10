package io.laminext.core
package ops.stream

import com.raquo.laminar.api.L._
import scala.concurrent.duration.FiniteDuration

final class EventStreamOps[A](underlying: EventStream[A]) {

  @inline def eitherT: EventStreamEitherT[Nothing, A] = new EventStreamEitherT(underlying.map(Right(_)))

  @inline def transitions: EventStream[(Option[A], A)] = new TransitionsEventStream(underlying)

  def previousAndLatest: Signal[(Option[A], Option[A])] = {
    underlying.scanLeft((Option.empty[A], Option.empty[A])) { case ((_, previous), current) =>
      (previous, Some(current))
    }
  }

  def previousAndLatestWithReset(reset: EventStream[Any]): Signal[(Option[A], Option[A])] = {
    EventStream
      .merge(
        underlying.map(Some(_)),
        reset.mapToStrict(None)
      )
      .scanLeft((Option.empty[A], Option.empty[A])) { case ((_, previous), maybeCurrent) =>
        maybeCurrent match {
          case Some(current) => (previous, Some(current))
          case None          => (None, None)
        }
      }
  }

  @inline def mapToTrue: EventStream[Boolean]  = underlying.mapToStrict(true)
  @inline def mapToFalse: EventStream[Boolean] = underlying.mapToStrict(false)

  def delayFor(projectMs: A => FiniteDuration): EventStream[A] = {
    new DelayForEventStream(parent = underlying, projectMs)
  }

  def skipWhen(b: Signal[Boolean]): EventStream[A] =
    underlying
      .withCurrentValueOf(b)
      .collect { case (v, false) => v }

  def keepWhen(b: Signal[Boolean]): EventStream[A] =
    underlying
      .withCurrentValueOf(b)
      .collect { case (v, true) => v }

  def flip(on: A, off: A, initial: Boolean = false): Signal[Boolean] =
    underlying.scanLeft(initial) { (prev, event) =>
      if (event == on) {
        true
      } else if (event == off) {
        false
      } else {
        prev
      }
    }

  def flip[T](toggle: PartialFunction[A, (T, Boolean)], initial: Boolean): Signal[Map[T, Boolean]] =
    underlying.scanLeft(Map.empty[T, Boolean].withDefaultValue(initial)) { (map, event) =>
      if (toggle.isDefinedAt(event)) {
        val (v, state) = toggle(event)
        map.updated(v, state)
      } else {
        map
      }
    }

  def errorOrValue: EventStream[Either[Throwable, A]] =
    underlying.recoverToTry.map(_.toEither)

}
