package io.laminext.core
package binders

import app.tulz.tuplez.Composition
import com.raquo.airstream.core.Observer
import com.raquo.airstream.eventbus.EventBus
import com.raquo.airstream.eventbus.WriteBus
import com.raquo.airstream.flatten.FlattenStrategy
import com.raquo.laminar.api.L._
import com.raquo.laminar.emitter.EventPropTransformation
import com.raquo.laminar.nodes.ReactiveElement
import org.scalajs.dom

import scala.util.Try

class ThisEventsStreamBinder[Ev <: dom.Event, A](
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

  def andThen[B](f: EventStream[A] => EventStream[B]): ThisEventsStreamBinder[Ev, B] = new ThisEventsStreamBinder(t, transform.andThen(f))

  // EventStream proxies

  @inline def map[B](project: A => B): ThisEventsStreamBinder[Ev, B] = andThen(_.map(project))

  @inline def mapTo[B](value: => B): ThisEventsStreamBinder[Ev, B] = map(_ => value)

  @inline def mapToValue[B](value: B): ThisEventsStreamBinder[Ev, B] = map(_ => value)

  @inline def mapToUnit: ThisEventsStreamBinder[Ev, Unit] = map(_ => (): Unit)

  @inline def mapToTrue: ThisEventsStreamBinder[Ev, Boolean] = map(_ => true)

  @inline def mapToFalse: ThisEventsStreamBinder[Ev, Boolean] = map(_ => false)

  @inline def flatMap[B](compose: A => EventStream[B])(implicit
    strategy: FlattenStrategy[EventStream, EventStream, EventStream]
  ): ThisEventsStreamBinder[Ev, B] =
    andThen(s => strategy.flatten(s.map(compose)))

  @inline def filter(passes: A => Boolean): ThisEventsStreamBinder[Ev, A] =
    andThen(_.filter(passes))

  @inline def filterNot(predicate: A => Boolean): ThisEventsStreamBinder[Ev, A] =
    andThen(_.filterNot(predicate))

  @inline def collect[B](pf: PartialFunction[A, B]): ThisEventsStreamBinder[Ev, B] =
    andThen(_.collect(pf))

  @inline def delay(ms: Int = 0): ThisEventsStreamBinder[Ev, A] = andThen(_.delay(ms))

  @inline def delaySync(after: EventStream[_]): ThisEventsStreamBinder[Ev, A] =
    andThen(_.delaySync(after))

  @inline def throttle(intervalMillis: Int): ThisEventsStreamBinder[Ev, A] =
    andThen(_.throttle(intervalMillis))

  @inline def debounce(delayFromLastEventMillis: Int): ThisEventsStreamBinder[Ev, A] =
    andThen(_.debounce(delayFromLastEventMillis))

  @inline def foldLeft[B](initial: B)(fn: (B, A) => B): ThisEventsSignalBinder[Ev, B] =
    new ThisEventsSignalBinder(t, transform.andThen(_.foldLeft(initial)(fn)))

  @inline def foldLeftRecover[B](
    initial: Try[B]
  )(fn: (Try[B], Try[A]) => Try[B]): ThisEventsSignalBinder[Ev, B] =
    new ThisEventsSignalBinder(t, transform.andThen(_.foldLeftRecover(initial)(fn)))

  @inline def startWith[B >: A](initial: => B): ThisEventsSignalBinder[Ev, B] =
    toSignal(initial)

  @inline def startWithTry[B >: A](
    initial: => Try[B]
  ): ThisEventsSignalBinder[Ev, B] = toSignalWithTry(initial)

  @inline def startWithNone: ThisEventsSignalBinder[Ev, Option[A]] = toWeakSignal

  @inline def toSignal[B >: A](initial: => B): ThisEventsSignalBinder[Ev, B] =
    new ThisEventsSignalBinder(t, transform = transform.andThen(_.toSignal(initial)))

  @inline def toSignalWithTry[B >: A](initial: => Try[B]): ThisEventsSignalBinder[Ev, B] =
    new ThisEventsSignalBinder(t, transform = transform.andThen(_.toSignalWithTry(initial)))

  @inline def toWeakSignal: ThisEventsSignalBinder[Ev, Option[A]] =
    new ThisEventsSignalBinder(t, transform = transform.andThen(_.toWeakSignal))

  @inline def compose[B](operator: EventStream[A] => EventStream[B]): ThisEventsStreamBinder[Ev, B] =
    andThen(_.compose(operator))

  @inline def combineWith[T1, Out](
    s1: EventStream[T1]
  )(implicit c: Composition[A, T1]): ThisEventsStreamBinder[Ev, c.Composed] =
    andThen(_.combine(s1))

  @inline def combineWith[T1, Out](
    otherEventStream: EventStream[T1]
  )(
    combinator: (A, T1) => Out
  ): ThisEventsStreamBinder[Ev, Out] =
    andThen(_.combineWith(otherEventStream)(combinator))

  @inline def withCurrentValueOf[T1](signal: Signal[T1]): ThisEventsStreamBinder[Ev, (A, T1)] =
    andThen(_.withCurrentValueOf(signal))

  @inline def sample[T1](signal: Signal[T1]): ThisEventsStreamBinder[Ev, T1] =
    andThen(_.sample(signal))

  @inline def debugLog(prefix: String = "event", when: A => Boolean = _ => true): ThisEventsStreamBinder[Ev, A] =
    andThen(_.debugLog(prefix, when))

  @inline def debugLogJs(prefix: String = "event", when: A => Boolean = _ => true): ThisEventsStreamBinder[Ev, A] =
    andThen(_.debugLogJs(prefix, when))

  @inline def debugBreak(when: A => Boolean = _ => true): ThisEventsStreamBinder[Ev, A] =
    andThen(_.debugBreak(when))

  @inline def debugSpy(fn: A => Unit): ThisEventsStreamBinder[Ev, A] =
    andThen(_.debugSpy(fn))

}
