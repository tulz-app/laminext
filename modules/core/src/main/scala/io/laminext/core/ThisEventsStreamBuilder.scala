package io.laminext.core

import app.tulz.tuplez.Composition
import com.raquo.airstream.core.Observer
import com.raquo.airstream.debug.Debugger
import com.raquo.airstream.eventbus.EventBus
import com.raquo.airstream.eventbus.WriteBus
import com.raquo.airstream.flatten.FlattenStrategy
import com.raquo.airstream.util.always
import com.raquo.laminar.api.L._
import com.raquo.laminar.emitter.EventPropTransformation
import com.raquo.laminar.nodes.ReactiveElement
import org.scalajs.dom

import scala.util.Try

class ThisEventsStreamBuilder[Ev <: dom.Event, A](
  t: EventPropTransformation[Ev, Ev],
  transform: EventStream[Ev] => EventStream[A]
) {

  @inline def forEach[El <: Element](onNext: A => Unit): Modifier[ReactiveElement.Base] =
    inContext(el => transform(el.events(t)) --> onNext)

  @inline def -->[El <: Element](observer: Observer[A]): Modifier[ReactiveElement.Base] =
    inContext(el => transform(el.events(t)) --> observer)

  @inline def -->[El <: Element](onNext: A => Unit): Modifier[ReactiveElement.Base] =
    inContext(el => transform(el.events(t)) --> onNext)

  @inline def -->[El <: Element](writeBus: WriteBus[A]): Modifier[ReactiveElement.Base] =
    inContext(el => transform(el.events(t)) --> writeBus)

  @inline def -->[El <: Element](eventBus: EventBus[A]): Modifier[ReactiveElement.Base] =
    inContext(el => transform(el.events(t)) --> eventBus)

  // ---

  def andThen[B](f: EventStream[A] => EventStream[B]): ThisEventsStreamBuilder[Ev, B] = new ThisEventsStreamBuilder(t, transform.andThen(f))

  // EventStream proxies

  @inline def map[B](project: A => B): ThisEventsStreamBuilder[Ev, B] = andThen(_.map(project))

  @inline def mapTo[B](value: => B): ThisEventsStreamBuilder[Ev, B] = map(_ => value)

  @inline def mapToValue[B](value: B): ThisEventsStreamBuilder[Ev, B] = map(_ => value)

  @inline def mapToUnit: ThisEventsStreamBuilder[Ev, Unit] = map(_ => (): Unit)

  @inline def mapToTrue: ThisEventsStreamBuilder[Ev, Boolean] = map(_ => true)

  @inline def mapToFalse: ThisEventsStreamBuilder[Ev, Boolean] = map(_ => false)

  @inline def flatMap[B](compose: A => EventStream[B])(implicit
    strategy: FlattenStrategy[EventStream, EventStream, EventStream]
  ): ThisEventsStreamBuilder[Ev, B] =
    andThen(s => strategy.flatten(s.map(compose)))

  @inline def filter(passes: A => Boolean): ThisEventsStreamBuilder[Ev, A] =
    andThen(_.filter(passes))

  @inline def filterNot(predicate: A => Boolean): ThisEventsStreamBuilder[Ev, A] =
    andThen(_.filterNot(predicate))

  @inline def collect[B](pf: PartialFunction[A, B]): ThisEventsStreamBuilder[Ev, B] =
    andThen(_.collect(pf))

  @inline def delay(ms: Int = 0): ThisEventsStreamBuilder[Ev, A] = andThen(_.delay(ms))

  @inline def delaySync(after: EventStream[_]): ThisEventsStreamBuilder[Ev, A] =
    andThen(_.delaySync(after))

  @inline def throttle(intervalMillis: Int): ThisEventsStreamBuilder[Ev, A] =
    andThen(_.throttle(intervalMillis))

  @inline def debounce(delayFromLastEventMillis: Int): ThisEventsStreamBuilder[Ev, A] =
    andThen(_.debounce(delayFromLastEventMillis))

  @inline def foldLeft[B](initial: B)(fn: (B, A) => B): ThisEventsSignalBuilder[Ev, B] =
    new ThisEventsSignalBuilder(t, transform.andThen(_.foldLeft(initial)(fn)))

  @inline def foldLeftRecover[B](
    initial: Try[B]
  )(fn: (Try[B], Try[A]) => Try[B]): ThisEventsSignalBuilder[Ev, B] =
    new ThisEventsSignalBuilder(t, transform.andThen(_.foldLeftRecover(initial)(fn)))

  @inline def startWith[B >: A](initial: => B): ThisEventsSignalBuilder[Ev, B] =
    toSignal(initial)

  @inline def startWithTry[B >: A](
    initial: => Try[B]
  ): ThisEventsSignalBuilder[Ev, B] = toSignalWithTry(initial)

  @inline def startWithNone: ThisEventsSignalBuilder[Ev, Option[A]] = toWeakSignal

  @inline def toSignal[B >: A](initial: => B): ThisEventsSignalBuilder[Ev, B] =
    new ThisEventsSignalBuilder(t, transform = transform.andThen(_.toSignal(initial)))

  @inline def toSignalWithTry[B >: A](initial: => Try[B]): ThisEventsSignalBuilder[Ev, B] =
    new ThisEventsSignalBuilder(t, transform = transform.andThen(_.toSignalWithTry(initial)))

  @inline def toWeakSignal: ThisEventsSignalBuilder[Ev, Option[A]] =
    new ThisEventsSignalBuilder(t, transform = transform.andThen(_.toWeakSignal))

  @inline def compose[B](operator: EventStream[A] => EventStream[B]): ThisEventsStreamBuilder[Ev, B] =
    andThen(_.compose(operator))

  @inline def combineWith[T1, Out](
    s1: EventStream[T1]
  )(implicit c: Composition[A, T1]): ThisEventsStreamBuilder[Ev, c.Composed] =
    andThen(_.combineWith(s1))

  @inline def combineWithFn[T1, Out](
    otherEventStream: EventStream[T1]
  )(
    combinator: (A, T1) => Out
  ): ThisEventsStreamBuilder[Ev, Out] =
    andThen(_.combineWithFn(otherEventStream)(combinator))

  @inline def withCurrentValueOf[T1](signal: Signal[T1]): ThisEventsStreamBuilder[Ev, (A, T1)] =
    andThen(_.withCurrentValueOf(signal))

  @inline def sample[T1](signal: Signal[T1]): ThisEventsStreamBuilder[Ev, T1] =
    andThen(_.sample(signal))

  @inline def setDisplayName(name: String): ThisEventsStreamBuilder[Ev, A] =
    andThen(_.setDisplayName(name))

  @inline def debugLog(
    when: Try[A] => Boolean = always,
    useJsLogger: Boolean = false
  ): ThisEventsStreamBuilder[Ev, A] =
    andThen(_.debugLog(when, useJsLogger))

  @inline def debugLogEvents(
    when: A => Boolean = always,
    useJsLogger: Boolean = false
  ): ThisEventsStreamBuilder[Ev, A] =
    andThen(_.debugLogEvents(when, useJsLogger))

  @inline def debugLogErrors(
    when: Throwable => Boolean = always
  ): ThisEventsStreamBuilder[Ev, A] =
    andThen(_.debugLogErrors(when))

  @inline def debugLogLifecycle(
    logStarts: Boolean = true,
    logStops: Boolean = true
  ): ThisEventsStreamBuilder[Ev, A] =
    andThen(_.debugLogLifecycle(logStarts, logStops))

  @inline def debugLogStarts: ThisEventsStreamBuilder[Ev, A] =
    andThen(_.debugLogStarts)

  @inline def debugLogStops: ThisEventsStreamBuilder[Ev, A] =
    andThen(_.debugLogStops)

  @inline def debugBreak(when: Try[A] => Boolean = always): ThisEventsStreamBuilder[Ev, A] =
    andThen(_.debugBreak(when))

  @inline def debugBreakEvents(when: A => Boolean = always): ThisEventsStreamBuilder[Ev, A] =
    andThen(_.debugBreakEvents(when))

  @inline def debugBreakErrors(when: Throwable => Boolean = always): ThisEventsStreamBuilder[Ev, A] =
    andThen(_.debugBreakErrors(when))

  @inline def debugBreakLifecycle: ThisEventsStreamBuilder[Ev, A] =
    andThen(_.debugBreakLifecycle)

  @inline def debugBreakStarts: ThisEventsStreamBuilder[Ev, A] =
    andThen(_.debugBreakStarts)

  @inline def debugBreakStops: ThisEventsStreamBuilder[Ev, A] =
    andThen(_.debugBreakStops)

  @inline def debugWithName(displayName: String): ThisEventsStreamBuilder[Ev, A] =
    andThen(_.debugWithName(displayName))

  @inline def debugSpy(fn: Try[A] => Unit): ThisEventsStreamBuilder[Ev, A] =
    andThen(_.debugSpy(fn))

  @inline def debugSpyEvents(fn: A => Unit): ThisEventsStreamBuilder[Ev, A] =
    andThen(_.debugSpyEvents(fn))

  @inline def debugSpyErrors(fn: Throwable => Unit): ThisEventsStreamBuilder[Ev, A] =
    andThen(_.debugSpyErrors(fn))

  @inline def debugSpyLifecycle(startFn: Int => Unit, stopFn: () => Unit): ThisEventsStreamBuilder[Ev, A] =
    andThen(_.debugSpyLifecycle(startFn, stopFn))

  @inline def debugSpyStarts(fn: Int => Unit): ThisEventsStreamBuilder[Ev, A] =
    andThen(_.debugSpyStarts(fn))

  @inline def debugSpyStops(fn: () => Unit): ThisEventsStreamBuilder[Ev, A] =
    andThen(_.debugSpyStops(fn))

  @inline def debugWith(debugger: Debugger[A]): ThisEventsStreamBuilder[Ev, A] =
    andThen(_.debugWith(debugger))

}
