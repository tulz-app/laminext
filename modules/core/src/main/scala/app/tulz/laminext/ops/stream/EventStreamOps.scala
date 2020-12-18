package app.tulz.laminext.ops.stream

import com.raquo.laminar.api.L._
import app.tulz.tuplez.Composition
import app.tulz.tuplez.TupleComposition
import com.raquo.airstream.eventstream.EventStream
import com.raquo.airstream.signal.Signal
import com.raquo.airstream.MapDelayEventStream
import com.raquo.laminar.modifiers.Binder
import com.raquo.laminar.nodes.ReactiveElement

import scala.util.Failure

final class EventStreamOps[A](underlying: EventStream[A]) {

  def transitions: EventStream[(Option[A], A)] = {
    EventStream
      .merge(
        EventStream.fromValue(Option.empty[A], emitOnce = true),
        underlying.map(a => Some(a))
      ).combineWith(
        new EventStreamOps(underlying).drop(1)
      )
  }

  def failures: EventStream[Throwable] = underlying.recoverToTry.collect { case Failure(err) => err }

  @inline def mapToUnit: EventStream[Unit]     = underlying.mapToValue((): Unit)
  @inline def mapToTrue: EventStream[Boolean]  = underlying.mapToValue(true)
  @inline def mapToFalse: EventStream[Boolean] = underlying.mapToValue(false)

  def mapDelay(projectMs: A => Double): EventStream[A] = {
    new MapDelayEventStream(parent = underlying, projectMs)
  }

  def skipWhen(b: Signal[Boolean]): EventStream[A] =
    underlying
      .withCurrentValueOf(b)
      .collect { case (v, false) =>
        v
      }

  def drop(count: Int): EventStream[A] = {
    var seen = 0
    underlying
      .map { event =>
        seen = seen + 1
        event
      }.filter(_ => seen >= count)
  }

  def take(count: Int): EventStream[A] = {
    var seen = 0
    underlying.filter(_ => seen < count).map { event =>
      seen = seen + 1
      event
    }
  }

  def withCurrentValueOfC[AA >: A, B](signal: Signal[B])(implicit composition: Composition[AA, B]): EventStream[composition.Composed] = {
    underlying.withCurrentValueOf(signal).map { case (a, b) =>
      TupleComposition.compose(a, b)
    }
  }

  def combineWithC[AA >: A, B](otherStream: EventStream[B])(implicit composition: Composition[AA, B]): EventStream[composition.Composed] = {
    underlying.combineWith(otherStream).map { case (a, b) =>
      TupleComposition.compose(a, b)
    }
  }

  def sampleStream[B](otherStream: EventStream[B]): EventStream[B] = {
    underlying.sample(otherStream.toWeakSignal).collect { case Some(b) =>
      b
    }
  }

  @inline def bind(onNext: A => Unit): Binder[ReactiveElement.Base] = underlying --> onNext

  @inline def bindCollect(onNext: PartialFunction[A, Unit]): Binder[ReactiveElement.Base] =
    underlying.filter(e => onNext.isDefinedAt(e)) --> onNext

}
