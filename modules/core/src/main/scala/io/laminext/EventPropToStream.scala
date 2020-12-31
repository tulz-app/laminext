package io.laminext

import io.laminext.ops.stream.EventStreamOps
import app.tulz.tuplez.Composition
import com.raquo.airstream.core.Observer
import com.raquo.airstream.features.FlattenStrategy
import com.raquo.laminar.api.L._
import com.raquo.laminar.keys.ReactiveEventProp
import com.raquo.laminar.modifiers.EventPropBinder
import com.raquo.laminar.nodes.ReactiveElement
import org.scalajs.dom

import scala.util.Try

final class EventPropToStream[Ev <: dom.Event, A](
  val key: ReactiveEventProp[Ev],
  val shouldUseCapture: Boolean,
  val shouldPreventDefault: Boolean,
  val shouldStopPropagation: Boolean,
  val transform: EventStream[Ev] => EventStream[A]
) {

  @inline def bind[El <: ReactiveElement.Base](
    onNext: A => Unit
  ): EventPropBinder[Ev] =
    new EventPropToStreamPropBinder(this, onNext)

  @inline def -->[El <: ReactiveElement.Base](
    onNext: A => Unit
  ): EventPropBinder[Ev] =
    new EventPropToStreamPropBinder(this, onNext)

  @inline def -->[El <: ReactiveElement.Base](
    observer: Observer[A]
  ): EventPropBinder[Ev] =
    this --> observer.onNext _

  @inline def -->[BusEv >: A, El <: ReactiveElement.Base](
    eventBus: EventBus[BusEv]
  ): EventPropBinder[Ev] =
    this --> eventBus.writer.onNext _

  def andThen[B](
    f: EventStream[A] => EventStream[B]
  ): EventPropToStream[Ev, B] =
    new EventPropToStream(
      key,
      shouldUseCapture,
      shouldPreventDefault,
      shouldStopPropagation,
      transform.andThen(f)
    )

  def useCapture: EventPropToStream[Ev, A] =
    new EventPropToStream(
      key,
      shouldUseCapture = true,
      shouldPreventDefault,
      shouldStopPropagation,
      transform
    )

  def useBubbleMode: EventPropToStream[Ev, A] =
    new EventPropToStream(
      key,
      shouldUseCapture = false,
      shouldPreventDefault,
      shouldStopPropagation,
      transform
    )

  def preventDefault: EventPropToStream[Ev, A] =
    new EventPropToStream(
      key,
      shouldUseCapture,
      shouldPreventDefault = true,
      shouldStopPropagation,
      transform
    )

  def stopPropagation: EventPropToStream[Ev, A] =
    new EventPropToStream(
      key,
      shouldUseCapture,
      shouldPreventDefault,
      shouldStopPropagation = true,
      transform
    )

  // EventStream proxies

  def map[B](project: A => B): EventPropToStream[Ev, B] =
    andThen(_.map(project))

  @inline def mapTo[B](value: => B): EventPropToStream[Ev, B] = map(_ => value)

  @inline def mapToValue[B](value: B): EventPropToStream[Ev, B] = map(_ => value)

  @inline def mapToUnit: EventPropToStream[Ev, Unit] = map(_ => (): Unit)

  @inline def mapToTrue: EventPropToStream[Ev, Boolean] = map(_ => true)

  @inline def mapToFalse: EventPropToStream[Ev, Boolean] = map(_ => false)

  @inline def flatMap[B](compose: A => EventStream[B])(implicit
    strategy: FlattenStrategy[EventStream, EventStream, EventStream]
  ): EventPropToStream[Ev, B] =
    andThen(s => strategy.flatten(s.map(compose)))

  def filter(passes: A => Boolean): EventPropToStream[Ev, A] =
    andThen(_.filter(passes))

  def filterNot(predicate: A => Boolean): EventPropToStream[Ev, A] =
    andThen(_.filterNot(predicate))

  def collect[B](pf: PartialFunction[A, B]): EventPropToStream[Ev, B] =
    andThen(_.collect(pf))

  def delay(ms: Int = 0): EventPropToStream[Ev, A] = andThen(_.delay(ms))

  def delaySync(after: EventStream[_]): EventPropToStream[Ev, A] =
    andThen(_.delaySync(after))

  def throttle(intervalMillis: Int): EventPropToStream[Ev, A] =
    andThen(_.throttle(intervalMillis))

  def debounce(delayFromLastEventMillis: Int): EventPropToStream[Ev, A] =
    andThen(_.debounce(delayFromLastEventMillis))

  def foldLeft[B](initial: B)(fn: (B, A) => B): EventPropToSignal[Ev, B] =
    new EventPropToSignal(
      key,
      shouldUseCapture,
      shouldPreventDefault,
      shouldStopPropagation,
      transform = transform.andThen(_.foldLeft(initial)(fn))
    )

  def foldLeftRecover[B](
    initial: Try[B]
  )(fn: (Try[B], Try[A]) => Try[B]): EventPropToSignal[Ev, B] =
    new EventPropToSignal(
      key,
      shouldUseCapture,
      shouldPreventDefault,
      shouldStopPropagation,
      transform = transform.andThen(_.foldLeftRecover(initial)(fn))
    )

  @inline def startWith[B >: A](initial: => B): EventPropToSignal[Ev, B] =
    toSignal(initial)

  @inline def startWithTry[B >: A](
    initial: => Try[B]
  ): EventPropToSignal[Ev, B] = toSignalWithTry(initial)

  @inline def startWithNone: EventPropToSignal[Ev, Option[A]] = toWeakSignal

  def toSignal[B >: A](initial: => B): EventPropToSignal[Ev, B] =
    new EventPropToSignal(
      key,
      shouldUseCapture,
      shouldPreventDefault,
      shouldStopPropagation,
      transform = transform.andThen(_.toSignal(initial))
    )

  def toSignalWithTry[B >: A](initial: => Try[B]): EventPropToSignal[Ev, B] =
    new EventPropToSignal(
      key,
      shouldUseCapture,
      shouldPreventDefault,
      shouldStopPropagation,
      transform = transform.andThen(_.toSignalWithTry(initial))
    )

  def toWeakSignal: EventPropToSignal[Ev, Option[A]] =
    new EventPropToSignal(
      key,
      shouldUseCapture,
      shouldPreventDefault,
      shouldStopPropagation,
      transform = transform.andThen(_.toWeakSignal)
    )

  def compose[B](
    operator: EventStream[A] => EventStream[B]
  ): EventPropToStream[Ev, B] =
    andThen(_.compose(operator))

  def combineWith[B](
    otherEventStream: EventStream[B]
  ): EventPropToStream[Ev, (A, B)] =
    andThen(_.combineWith(otherEventStream))

  def withCurrentValueOf[B](signal: Signal[B]): EventPropToStream[Ev, (A, B)] =
    andThen(_.withCurrentValueOf(signal))

  def withCurrentValueOfC[AA >: A, B](signal: Signal[B])(implicit
    compose: Composition[AA, B]
  ): EventPropToStream[Ev, compose.Composed] =
    andThen(s => new EventStreamOps(s).withCurrentValueOfC(signal))

  def sample[B](signal: Signal[B]): EventPropToStream[Ev, B] =
    andThen(_.sample(signal))

  def debugLog(prefix: String = "event", when: A => Boolean = _ => true): EventPropToStream[Ev, A] =
    andThen(_.debugLog(prefix, when))

  def debugLogJs(prefix: String = "event", when: A => Boolean = _ => true): EventPropToStream[Ev, A] =
    andThen(_.debugLogJs(prefix, when))

  def debugBreak(when: A => Boolean = _ => true): EventPropToStream[Ev, A] =
    andThen(_.debugBreak(when))

  def debugSpy(fn: A => Unit): EventPropToStream[Ev, A] =
    andThen(_.debugSpy(fn))

}
