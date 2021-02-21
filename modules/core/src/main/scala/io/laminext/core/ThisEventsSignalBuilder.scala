package io.laminext.core

import app.tulz.tuplez.Composition
import com.raquo.airstream.core.Sink
import com.raquo.airstream.debug.Debugger
import com.raquo.airstream.util.always
import com.raquo.laminar.api.L._
import com.raquo.laminar.modifiers.Binder
import com.raquo.laminar.nodes.ReactiveElement
import org.scalajs.dom

import scala.util.Failure
import scala.util.Try

class ThisEventsSignalBuilder[Ev <: dom.Event, A](
  t: EventProcessor[Ev, Ev],
  transform: EventStream[Ev] => Signal[A]
) {

  @inline def -->[El <: Element](sink: Sink[A]): Binder[ReactiveElement.Base] =
    composeEvents(t)(transform) --> sink

  @inline def -->[El <: Element](onNext: A => Unit): Modifier[ReactiveElement.Base] =
    composeEvents(t)(transform) --> onNext

  // ---

  @inline def andThen[B](f: Signal[A] => Signal[B]): ThisEventsSignalBuilder[Ev, B] =
    new ThisEventsSignalBuilder(t, transform.andThen(f))

  // Signal proxies

  @inline def map[B](project: A => B): ThisEventsSignalBuilder[Ev, B] =
    andThen(_.map(project))

  @inline def compose[B](operator: Signal[A] => Signal[B]): ThisEventsSignalBuilder[Ev, B] =
    andThen(_.compose(operator))

  @inline def composeChanges[AA >: A](
    operator: EventStream[A] => EventStream[AA]
  ): ThisEventsSignalBuilder[Ev, AA] = andThen(_.composeChanges(operator))

  @inline def composeAll[B](
    operator: EventStream[A] => EventStream[B],
    initialOperator: Try[A] => Try[B]
  ): ThisEventsSignalBuilder[Ev, B] =
    andThen(_.composeAll(operator, initialOperator))

  @inline def changes: ThisEventsStreamBuilder[Ev, A] =
    new ThisEventsStreamBuilder(t, transform.andThen(_.changes))

  @inline def foldLeft[B](makeInitial: A => B)(fn: (B, A) => B): ThisEventsSignalBuilder[Ev, B] =
    andThen(_.foldLeft(makeInitial)(fn))

  @inline def foldLeftRecover[B](makeInitial: Try[A] => Try[B])(
    fn: (Try[B], Try[A]) => Try[B]
  ): ThisEventsSignalBuilder[Ev, B] =
    andThen(_.foldLeftRecover(makeInitial)(fn))

  @inline def recover[B >: A](
    pf: PartialFunction[Throwable, Option[B]]
  ): ThisEventsSignalBuilder[Ev, B] =
    andThen(_.recover(pf))

  @inline def recoverToTry: ThisEventsSignalBuilder[Ev, Try[A]] =
    map(Try(_)).recover[Try[A]] { case err => Some(Failure(err)) }

  @inline def combineWithFn[T1, Out](
    s1: Signal[T1]
  )(
    combinator: (A, T1) => Out
  ): ThisEventsSignalBuilder[Ev, Out] =
    andThen(_.combineWithFn(s1)(combinator))

  @inline def combineWith[T1](
    s1: Signal[T1]
  )(implicit c: Composition[A, T1]): ThisEventsSignalBuilder[Ev, c.Composed] =
    andThen(_.combineWith(s1))

  @inline def debugWith(debugger: Debugger[A]): ThisEventsSignalBuilder[Ev, A] =
    andThen(_.debugWith(debugger))

  @inline def setDisplayName(name: String): ThisEventsSignalBuilder[Ev, A] =
    andThen(_.setDisplayName(name))

  @inline def debugLog(
    when: Try[A] => Boolean = always,
    useJsLogger: Boolean = false
  ): ThisEventsSignalBuilder[Ev, A] =
    andThen(_.debugLog(when, useJsLogger))

  @inline def debugLogEvents(
    when: A => Boolean = always,
    useJsLogger: Boolean = false
  ): ThisEventsSignalBuilder[Ev, A] =
    andThen(_.debugLogEvents(when, useJsLogger))

  @inline def debugLogErrors(
    when: Throwable => Boolean = always
  ): ThisEventsSignalBuilder[Ev, A] =
    andThen(_.debugLogErrors(when))

  @inline def debugLogLifecycle(
    logStarts: Boolean = true,
    logStops: Boolean = true
  ): ThisEventsSignalBuilder[Ev, A] =
    andThen(_.debugLogLifecycle(logStarts, logStops))

  @inline def debugLogStarts: ThisEventsSignalBuilder[Ev, A] =
    andThen(_.debugLogStarts)

  @inline def debugLogStops: ThisEventsSignalBuilder[Ev, A] =
    andThen(_.debugLogStops)

  @inline def debugBreak(when: Try[A] => Boolean = always): ThisEventsSignalBuilder[Ev, A] =
    andThen(_.debugBreak(when))

  @inline def debugBreakEvents(when: A => Boolean = always): ThisEventsSignalBuilder[Ev, A] =
    andThen(_.debugBreakEvents(when))

  @inline def debugBreakErrors(when: Throwable => Boolean = always): ThisEventsSignalBuilder[Ev, A] =
    andThen(_.debugBreakErrors(when))

  @inline def debugBreakLifecycle: ThisEventsSignalBuilder[Ev, A] =
    andThen(_.debugBreakLifecycle)

  @inline def debugBreakStarts: ThisEventsSignalBuilder[Ev, A] =
    andThen(_.debugBreakStarts)

  @inline def debugBreakStops: ThisEventsSignalBuilder[Ev, A] =
    andThen(_.debugBreakStops)

  @inline def debugWithName(displayName: String): ThisEventsSignalBuilder[Ev, A] =
    andThen(_.debugWithName(displayName))

  @inline def debugSpy(fn: Try[A] => Unit): ThisEventsSignalBuilder[Ev, A] =
    andThen(_.debugSpy(fn))

  @inline def debugSpyEvents(fn: A => Unit): ThisEventsSignalBuilder[Ev, A] =
    andThen(_.debugSpyEvents(fn))

  @inline def debugSpyErrors(fn: Throwable => Unit): ThisEventsSignalBuilder[Ev, A] =
    andThen(_.debugSpyErrors(fn))

  @inline def debugSpyLifecycle(startFn: Int => Unit, stopFn: () => Unit): ThisEventsSignalBuilder[Ev, A] =
    andThen(_.debugSpyLifecycle(startFn, stopFn))

  @inline def debugSpyStarts(fn: Int => Unit): ThisEventsSignalBuilder[Ev, A] =
    andThen(_.debugSpyStarts(fn))

  @inline def debugSpyStops(fn: () => Unit): ThisEventsSignalBuilder[Ev, A] =
    andThen(_.debugSpyStops(fn))

}
