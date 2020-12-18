package app.tulz.laminext

import app.tulz.laminext.ops.signal.SignalOps
import app.tulz.tuplez.Composition
import com.raquo.airstream.core.Observer
import com.raquo.laminar.api.L._
import com.raquo.laminar.keys.ReactiveEventProp
import com.raquo.laminar.modifiers.EventPropBinder
import com.raquo.laminar.nodes.ReactiveElement
import org.scalajs.dom

import scala.util.Try

class EventPropToSignal[Ev <: dom.Event, A](
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
    new EventPropToSignal(
      key,
      shouldUseCapture,
      shouldPreventDefault,
      shouldStopPropagation,
      transform.andThen(_.map(project))
    )

  def compose[B](operator: Signal[A] => Signal[B]): EventPropToSignal[Ev, B] =
    new EventPropToSignal(
      key,
      shouldUseCapture,
      shouldPreventDefault,
      shouldStopPropagation,
      transform.andThen(_.compose(operator))
    )

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
    new EventPropToSignal(
      key,
      shouldUseCapture,
      shouldPreventDefault,
      shouldStopPropagation,
      transform.andThen(_.composeAll(operator, initialOperator))
    )

  def combineWith[AA >: A, B](otherSignal: Signal[B]): EventPropToSignal[Ev, (AA, B)] =
    new EventPropToSignal(
      key,
      shouldUseCapture,
      shouldPreventDefault,
      shouldStopPropagation,
      transform.andThen(_.combineWith(otherSignal))
    )

  def combineWithC[AA >: A, B](otherSignal: Signal[B])(implicit compose: Composition[AA, B]): EventPropToSignal[Ev, Composition[AA, B]#Composed] =
    new EventPropToSignal(
      key,
      shouldUseCapture,
      shouldPreventDefault,
      shouldStopPropagation,
      transform.andThen(s => new SignalOps(s).combineWithC(otherSignal))
    )

  def changes: EventPropToStream[Ev, A] =
    new EventPropToStream(
      key,
      shouldUseCapture,
      shouldPreventDefault,
      shouldStopPropagation,
      transform.andThen(_.changes)
    )

  def foldLeft[B](makeInitial: A => B)(fn: (B, A) => B): EventPropToSignal[Ev, B] =
    new EventPropToSignal(
      key,
      shouldUseCapture,
      shouldPreventDefault,
      shouldStopPropagation,
      transform.andThen(_.foldLeft(makeInitial)(fn))
    )

}
