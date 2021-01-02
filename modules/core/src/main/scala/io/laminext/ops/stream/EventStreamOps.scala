package io.laminext.ops.stream

import com.raquo.laminar.api.L._
import app.tulz.tuplez.Composition
import app.tulz.tuplez.TupleComposition
import com.raquo.airstream.eventstream.DelayForEventStream
import com.raquo.airstream.eventstream.DropEventStream
import com.raquo.airstream.eventstream.EventStream
import com.raquo.airstream.eventstream.TakeEventStream
import com.raquo.airstream.eventstream.TransitionsEventStream
import com.raquo.airstream.signal.Signal
import com.raquo.laminar.modifiers.Binder
import com.raquo.laminar.nodes.ReactiveElement

import scala.util.Failure

final class EventStreamOps[A](underlying: EventStream[A]) {

  @inline def transitions: EventStream[(Option[A], A)] = new TransitionsEventStream(underlying)

  def failures: EventStream[Throwable] = underlying.recoverToTry.collect { case Failure(err) => err }

  @inline def mapToUnit: EventStream[Unit]     = underlying.mapToValue((): Unit)
  @inline def mapToTrue: EventStream[Boolean]  = underlying.mapToValue(true)
  @inline def mapToFalse: EventStream[Boolean] = underlying.mapToValue(false)

  def delayFor(projectMs: A => Double): EventStream[A] = {
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

  def withCurrentValueOfC[B](signal: Signal[B])(implicit composition: Composition[A, B]): EventStream[composition.Composed] = {
    underlying.withCurrentValueOf(signal).map { case (a, b) =>
      TupleComposition.compose(a, b)
    }
  }

  def combine[B](otherStream: EventStream[B])(implicit composition: Composition[A, B]): EventStream[composition.Composed] = {
    underlying.combineWith(otherStream).map { case (a, b) =>
      TupleComposition.compose(a, b)
    }
  }

  @inline def bind(onNext: A => Unit): Binder[ReactiveElement.Base] = underlying --> onNext

  @inline def bindCollect(onNext: PartialFunction[A, Unit]): Binder[ReactiveElement.Base] =
    underlying.filter(e => onNext.isDefinedAt(e)) --> onNext

  def previousAndCurrent: Signal[(Option[A], Option[A])] = {
    underlying.foldLeft((Option.empty[A], Option.empty[A])) { (previous, current) =>
      (previous._2, Some(current))
    }
  }

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

  def previousAndCurrentTupleWithReset(reset: EventStream[Any]): Signal[(Option[A], Option[A])] = {
    EventStream
      .merge(
        underlying.map(Some(_)),
        reset.mapToValue(None)
      ).foldLeft((Option.empty[A], Option.empty[A])) { (previous, maybeCurrent) =>
        maybeCurrent match {
          case Some(current) => (previous._2, Some(current))
          case None          => (None, None)
        }
      }
  }

  def switchedBy(on: A, off: A, initial: Boolean = false): Signal[Boolean] =
    underlying.foldLeft(initial) { (prev, event) =>
      if (event == on) {
        true
      } else if (event == off) {
        false
      } else {
        prev
      }
    }

  def switchedBy[T](toggle: PartialFunction[A, (T, Boolean)], initial: Boolean): Signal[Map[T, Boolean]] =
    underlying.foldLeft(Map.empty[T, Boolean].withDefaultValue(initial)) { (map, event) =>
      if (toggle.isDefinedAt(event)) {
        val (v, state) = toggle(event)
        map.updated(v, state)
      } else {
        map
      }
    }

}
