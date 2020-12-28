package app.tulz.laminext

import app.tulz.laminext.ops.signal.SignalOps
import app.tulz.tuplez.Composition
import com.raquo.airstream.core.Observer
import com.raquo.laminar.api.L._
import com.raquo.laminar.keys.ReactiveEventProp
import com.raquo.laminar.modifiers.EventPropBinder
import com.raquo.laminar.nodes.ReactiveElement
import org.scalajs.dom

import scala.scalajs.js
import scala.util.Failure
import scala.util.Try

final class EventPropToSignal[Ev <: dom.Event, A](
  val key: ReactiveEventProp[Ev],
  val shouldUseCapture: Boolean,
  val shouldPreventDefault: Boolean,
  val shouldStopPropagation: Boolean,
  val transform: EventStream[Ev] => Signal[A]
) {

  @inline def -->[El <: ReactiveElement.Base](onNext: A => Unit): EventPropBinder[Ev] =
    new EventPropToSignalPropBinder(this, onNext)

  @inline def -->[El <: ReactiveElement.Base](observer: Observer[A]): EventPropBinder[Ev] =
    this --> observer.onNext _

  @inline def -->[BusEv >: A, El <: ReactiveElement.Base](eventBus: EventBus[BusEv]): EventPropBinder[Ev] =
    this --> eventBus.writer.onNext _

  def andThen[B](f: Signal[A] => Signal[B]): EventPropToSignal[Ev, B] =
    new EventPropToSignal(
      key,
      shouldUseCapture,
      shouldPreventDefault,
      shouldStopPropagation,
      transform.andThen(f)
    )

  def useCapture: EventPropToSignal[Ev, A] =
    new EventPropToSignal(
      key,
      shouldUseCapture = true,
      shouldPreventDefault,
      shouldStopPropagation,
      transform
    )

  def useBubbleMode: EventPropToSignal[Ev, A] =
    new EventPropToSignal(
      key,
      shouldUseCapture = false,
      shouldPreventDefault,
      shouldStopPropagation,
      transform
    )

  def preventDefault: EventPropToSignal[Ev, A] =
    new EventPropToSignal(
      key,
      shouldUseCapture,
      shouldPreventDefault = true,
      shouldStopPropagation,
      transform
    )

  def stopPropagation: EventPropToSignal[Ev, A] =
    new EventPropToSignal(
      key,
      shouldUseCapture,
      shouldPreventDefault,
      shouldStopPropagation = true,
      transform
    )

  // Signal proxies

  def map[B](project: A => B): EventPropToSignal[Ev, B] =
    andThen(_.map(project))

  def compose[B](operator: Signal[A] => Signal[B]): EventPropToSignal[Ev, B] =
    andThen(_.compose(operator))

  def composeChanges[AA >: A](
    operator: EventStream[A] => EventStream[AA]
  ): EventPropToSignal[Ev, AA] =
    new EventPropToSignal(
      key,
      shouldUseCapture,
      shouldPreventDefault,
      shouldStopPropagation,
      transform.andThen(_.composeChanges(operator))
    )

  def composeAll[B](
    operator: EventStream[A] => EventStream[B],
    initialOperator: Try[A] => Try[B]
  ): EventPropToSignal[Ev, B] =
    andThen(_.composeAll(operator, initialOperator))

  def combineWith[B](otherSignal: Signal[B]): EventPropToSignal[Ev, (A, B)] =
    andThen(_.combineWith(otherSignal))

  def combine[B](otherSignal: Signal[B])(implicit compose: Composition[A, B]): EventPropToSignal[Ev, compose.Composed] =
    andThen(s => new SignalOps(s).combine(otherSignal))

  def changes: EventPropToStream[Ev, A] =
    new EventPropToStream(
      key,
      shouldUseCapture,
      shouldPreventDefault,
      shouldStopPropagation,
      transform.andThen(_.changes)
    )

  def foldLeft[B](makeInitial: A => B)(fn: (B, A) => B): EventPropToSignal[Ev, B] =
    andThen(_.foldLeft(makeInitial)(fn))

  def foldLeftRecover[B](makeInitial: Try[A] => Try[B])(fn: (Try[B], Try[A]) => Try[B]): EventPropToSignal[Ev, B] =
    andThen(_.foldLeftRecover(makeInitial)(fn))

  def recover[B >: A](pf: PartialFunction[Throwable, Option[B]]): EventPropToSignal[Ev, B] =
    andThen(_.recover(pf))

  def recoverToTry: EventPropToSignal[Ev, Try[A]] = map(Try(_)).recover[Try[A]] { case err => Some(Failure(err)) }

  def debugLog(prefix: String = "event", when: A => Boolean = _ => true): EventPropToSignal[Ev, A] =
    andThen(_.debugLog(prefix, when))

  def debugLogJs(prefix: String = "event", when: A => Boolean = _ => true): EventPropToSignal[Ev, A] =
    andThen(_.debugLog(prefix, when))

  def debugBreak(when: A => Boolean = _ => true): EventPropToSignal[Ev, A] =
    andThen(_.debugBreak(when))

  def debugSpy(fn: A => Unit): EventPropToSignal[Ev, A] =
    andThen(_.debugSpy(fn))

}
