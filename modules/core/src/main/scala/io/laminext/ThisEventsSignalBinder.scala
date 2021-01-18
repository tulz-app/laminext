package io.laminext

import app.tulz.tuplez.Composition
import com.raquo.airstream.core.Observer
import com.raquo.laminar.api.L._
import com.raquo.laminar.emitter.EventPropTransformation
import com.raquo.laminar.nodes.ReactiveElement
import org.scalajs.dom

import scala.util.Failure
import scala.util.Try

class ThisEventsSignalBinder[Ev <: dom.Event, A](
  t: EventPropTransformation[Ev, Ev],
  transform: EventStream[Ev] => Signal[A]
) {

  @inline def -->[El <: Element](observer: Observer[A]): Modifier[ReactiveElement.Base] =
    inContext(el => transform(el.events(t)) --> observer)

  @inline def -->[El <: Element](onNext: A => Unit): Modifier[ReactiveElement.Base] =
    inContext(el => transform(el.events(t)) --> onNext)

  // ---

  @inline def andThen[B](f: Signal[A] => Signal[B]): ThisEventsSignalBinder[Ev, B] =
    new ThisEventsSignalBinder(t, transform.andThen(f))

  // Signal proxies

  @inline def map[B](project: A => B): ThisEventsSignalBinder[Ev, B] =
    andThen(_.map(project))

  @inline def compose[B](operator: Signal[A] => Signal[B]): ThisEventsSignalBinder[Ev, B] =
    andThen(_.compose(operator))

  @inline def composeChanges[AA >: A](
    operator: EventStream[A] => EventStream[AA]
  ): ThisEventsSignalBinder[Ev, AA] = andThen(_.composeChanges(operator))

  @inline def composeAll[B](
    operator: EventStream[A] => EventStream[B],
    initialOperator: Try[A] => Try[B]
  ): ThisEventsSignalBinder[Ev, B] =
    andThen(_.composeAll(operator, initialOperator))

  @inline def combineWith[T1, Out](
    s1: Signal[T1]
  )(
    combinator: (A, T1) => Out
  ): ThisEventsSignalBinder[Ev, Out] =
    andThen(_.combineWith(s1)(combinator))

  @inline def combine[T1](
    s1: Signal[T1]
  )(implicit c: Composition[A, T1]): ThisEventsSignalBinder[Ev, c.Composed] =
    andThen(_.combine(s1))

  @inline def changes: ThisEventsStreamBinder[Ev, A] =
    new ThisEventsStreamBinder(t, transform.andThen(_.changes))

  @inline def foldLeft[B](makeInitial: A => B)(fn: (B, A) => B): ThisEventsSignalBinder[Ev, B] =
    andThen(_.foldLeft(makeInitial)(fn))

  @inline def foldLeftRecover[B](makeInitial: Try[A] => Try[B])(
    fn: (Try[B], Try[A]) => Try[B]
  ): ThisEventsSignalBinder[Ev, B] =
    andThen(_.foldLeftRecover(makeInitial)(fn))

  @inline def recover[B >: A](
    pf: PartialFunction[Throwable, Option[B]]
  ): ThisEventsSignalBinder[Ev, B] =
    andThen(_.recover(pf))

  @inline def recoverToTry: ThisEventsSignalBinder[Ev, Try[A]] =
    map(Try(_)).recover[Try[A]] { case err => Some(Failure(err)) }

//  @inline def debugLog(prefix: String = "event", when: A => Boolean = _ => true): ThisEventsSignalBinder[Ev, A] =
//    andThen(_.debugLog(prefix, when))
//
//  @inline def debugLogJs(prefix: String = "event", when: A => Boolean = _ => true): ThisEventsSignalBinder[Ev, A] =
//    andThen(_.debugLog(prefix, when))
//
//  @inline def debugBreak(when: A => Boolean = _ => true): ThisEventsSignalBinder[Ev, A] =
//    andThen(_.debugBreak(when))

  @inline def debugSpy(fn: A => Unit): ThisEventsSignalBinder[Ev, A] =
    andThen(_.debugSpy(fn))

}
