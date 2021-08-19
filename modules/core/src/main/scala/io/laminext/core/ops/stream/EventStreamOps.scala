package io.laminext.core
package ops.stream

import com.raquo.laminar.api.L._
import com.raquo.airstream.flatten.FlattenStrategy
import scala.concurrent.duration.FiniteDuration

final class EventStreamOps[A](underlying: EventStream[A]) {

  @inline def eitherT: EventStreamEitherT[Nothing, A] = new EventStreamEitherT(underlying.map(Right(_)))

  @inline def transitions: EventStream[(Option[A], A)] = new TransitionsEventStream(underlying)

  def previousAndLatest: Signal[(Option[A], Option[A])] = {
    underlying.foldLeft((Option.empty[A], Option.empty[A])) { case ((_, previous), current) =>
      (previous, Some(current))
    }
  }

  def previousAndLatestWithReset(reset: EventStream[Any]): Signal[(Option[A], Option[A])] = {
    EventStream
      .merge(
        underlying.map(Some(_)),
        reset.mapToStrict(None)
      ).foldLeft((Option.empty[A], Option.empty[A])) { case ((_, previous), maybeCurrent) =>
        maybeCurrent match {
          case Some(current) => (previous, Some(current))
          case None          => (None, None)
        }
      }
  }

  @inline def mapToUnit: EventStream[Unit]     = underlying.mapToStrict((): Unit)
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

  @inline def drop(toDrop: Int): EventStream[A] = new DropEventStream[A](underlying, toDrop)

  @inline def take(toTake: Int): EventStream[A] = new TakeEventStream[A](underlying, toTake)

  // TODO extract into a DistinctEventStream
  def distinct: EventStream[A] = {
    var previous = Option.empty[A]
    underlying.filter { event =>
      previous match {
        case Some(prev) if prev == event => false
        case _ =>
          previous = Some(event)
          true
      }
    }
  }

  def flip(on: A, off: A, initial: Boolean = false): Signal[Boolean] =
    underlying.foldLeft(initial) { (prev, event) =>
      if (event == on) {
        true
      } else if (event == off) {
        false
      } else {
        prev
      }
    }

  def flip[T](toggle: PartialFunction[A, (T, Boolean)], initial: Boolean): Signal[Map[T, Boolean]] =
    underlying.foldLeft(Map.empty[T, Boolean].withDefaultValue(initial)) { (map, event) =>
      if (toggle.isDefinedAt(event)) {
        val (v, state) = toggle(event)
        map.updated(v, state)
      } else {
        map
      }
    }

  def errorOrValue: EventStream[Either[Throwable, A]] =
    underlying.recoverToTry.map(_.toEither)

  @inline def flatMapTo[B, Inner[_], Output[+_] <: Observable[_]](
    inner: => Inner[B]
  )(implicit strategy: FlattenStrategy[EventStream, Inner, Output]): Output[B] = {
    underlying.flatMap(_ => inner)(strategy)
  }

}
