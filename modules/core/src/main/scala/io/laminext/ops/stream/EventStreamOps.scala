package io.laminext.ops.stream

import com.raquo.laminar.api.L._
import com.raquo.airstream.eventstream.DelayForEventStream
import com.raquo.airstream.eventstream.DropEventStream
import com.raquo.airstream.core.EventStream
import com.raquo.airstream.eventstream.TakeEventStream
import com.raquo.airstream.eventstream.TransitionsEventStream

import scala.concurrent.duration.FiniteDuration
import scala.util.Failure

final class EventStreamOps[A](underlying: EventStream[A]) {

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
        reset.mapToValue(None)
      ).foldLeft((Option.empty[A], Option.empty[A])) { case ((_, previous), maybeCurrent) =>
        maybeCurrent match {
          case Some(current) => (previous, Some(current))
          case None          => (None, None)
        }
      }
  }

  def errors: EventStream[Throwable] = underlying.recoverToTry.collect { case Failure(err) => err }

  @inline def mapToUnit: EventStream[Unit]     = underlying.mapToValue((): Unit)
  @inline def mapToTrue: EventStream[Boolean]  = underlying.mapToValue(true)
  @inline def mapToFalse: EventStream[Boolean] = underlying.mapToValue(false)

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

}
