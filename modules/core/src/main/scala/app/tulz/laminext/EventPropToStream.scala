package app.tulz.laminext

import app.tulz.laminext.ops.stream.EventStreamOps
import app.tulz.tuplez.Composition
import com.raquo.airstream.core.Observer
import com.raquo.airstream.features.FlattenStrategy
import com.raquo.laminar.api.L._
import com.raquo.laminar.emitter.EventPropTransformation
import com.raquo.laminar.keys.ReactiveEventProp
import com.raquo.laminar.modifiers.EventPropBinder
import com.raquo.laminar.nodes.ReactiveElement
import org.scalajs.dom

import scala.scalajs.js
import scala.util.Try
import scala.util.chaining._

class EventPropToStream[Ev <: dom.Event, A](
  val key: ReactiveEventProp[Ev],
  val shouldUseCapture: Boolean,
  val shouldPreventDefault: Boolean,
  val shouldStopPropagation: Boolean,
  val transform: EventStream[Ev] => EventStream[A]
) {

  @inline def -->[El <: ReactiveElement.Base](onNext: A => Unit): EventPropBinder[Ev] =
    new EventPropToStreamPropBinder(this, onNext)

  @inline def -->[El <: ReactiveElement.Base](observer: Observer[A]): EventPropBinder[Ev] =
    this --> observer.onNext _

  @inline def -->[BusEv >: A, El <: ReactiveElement.Base](eventBus: EventBus[BusEv]): EventPropBinder[Ev] =
    this --> eventBus.writer.onNext _

  def toTransformation: EventPropTransformation[Ev, Ev] = {
    eventPropToEventPropTransformation(key)
      .tap { p =>
        if (shouldUseCapture) {
          p.useCapture
        } else {
          p.useBubbleMode
        }
      }
      .tap { p =>
        if (shouldStopPropagation) {
          p.stopPropagation
        }
      }
      .tap { p =>
        if (shouldPreventDefault) {
          p.preventDefault
        }
      }
  }

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
    new EventPropToStream(
      key,
      shouldUseCapture,
      shouldPreventDefault,
      shouldStopPropagation,
      transform = transform.andThen(_.map(project))
    )

  def mapTo[B](value: => B): EventPropToStream[Ev, B] = map(_ => value)

  def mapToValue[B](value: B): EventPropToStream[Ev, B] = map(_ => value)

  @inline def flatMap[B](compose: A => EventStream[B])(implicit
    strategy: FlattenStrategy[EventStream, EventStream, EventStream]
  ): EventPropToStream[Ev, B] =
    new EventPropToStream(
      key,
      shouldUseCapture,
      shouldPreventDefault,
      shouldStopPropagation,
      transform = transform.andThen(s => strategy.flatten(s.map(compose)))
    )

  def filter(passes: A => Boolean): EventPropToStream[Ev, A] =
    new EventPropToStream(
      key,
      shouldUseCapture,
      shouldPreventDefault,
      shouldStopPropagation,
      transform = transform.andThen(_.filter(passes))
    )

  def filterNot(predicate: A => Boolean): EventPropToStream[Ev, A] =
    new EventPropToStream(
      key,
      shouldUseCapture,
      shouldPreventDefault,
      shouldStopPropagation,
      transform = transform.andThen(_.filterNot(predicate))
    )

  def collect[B](pf: PartialFunction[A, B]): EventPropToStream[Ev, B] =
    new EventPropToStream(
      key,
      shouldUseCapture,
      shouldPreventDefault,
      shouldStopPropagation,
      transform = transform.andThen(_.collect(pf))
    )

  def delay(ms: Int = 0): EventPropToStream[Ev, A] =
    new EventPropToStream(
      key,
      shouldUseCapture,
      shouldPreventDefault,
      shouldStopPropagation,
      transform = transform.andThen(_.delay(ms))
    )

  def delaySync(after: EventStream[_]): EventPropToStream[Ev, A] =
    new EventPropToStream(
      key,
      shouldUseCapture,
      shouldPreventDefault,
      shouldStopPropagation,
      transform = transform.andThen(_.delaySync(after))
    )

  def throttle(intervalMillis: Int): EventPropToStream[Ev, A] =
    new EventPropToStream(
      key,
      shouldUseCapture,
      shouldPreventDefault,
      shouldStopPropagation,
      transform = transform.andThen(_.throttle(intervalMillis))
    )

  def debounce(delayFromLastEventMillis: Int): EventPropToStream[Ev, A] =
    new EventPropToStream(
      key,
      shouldUseCapture,
      shouldPreventDefault,
      shouldStopPropagation,
      transform = transform.andThen(_.debounce(delayFromLastEventMillis))
    )

  def foldLeft[B](initial: B)(fn: (B, A) => B): EventPropToSignal[Ev, B] =
    new EventPropToSignal(
      key,
      shouldUseCapture,
      shouldPreventDefault,
      shouldStopPropagation,
      transform = transform.andThen(_.foldLeft(initial)(fn))
    )

  @inline def startWith[B >: A](initial: => B): EventPropToSignal[Ev, B] = toSignal(initial)

  @inline def startWithTry[B >: A](initial: => Try[B]): EventPropToSignal[Ev, B] = toSignalWithTry(initial)

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

  def compose[B](operator: EventStream[A] => EventStream[B]): EventPropToStream[Ev, B] =
    new EventPropToStream[Ev, B](
      key,
      shouldUseCapture,
      shouldPreventDefault,
      shouldStopPropagation,
      transform = transform.andThen(_.compose(operator))
    )

  def combineWith[AA >: A, B](otherEventStream: EventStream[B]): EventPropToStream[Ev, (AA, B)] =
    new EventPropToStream(
      key,
      shouldUseCapture,
      shouldPreventDefault,
      shouldStopPropagation,
      transform = transform.andThen(_.combineWith(otherEventStream))
    )

  def withCurrentValueOf[B](signal: Signal[B]): EventPropToStream[Ev, (A, B)] =
    new EventPropToStream(
      key,
      shouldUseCapture,
      shouldPreventDefault,
      shouldStopPropagation,
      transform = transform.andThen(_.withCurrentValueOf(signal))
    )

  def withCurrentValueOfC[AA >: A, B](signal: Signal[B])(implicit compose: Composition[AA, B]): EventPropToStream[Ev, compose.Composed] =
    new EventPropToStream(
      key,
      shouldUseCapture,
      shouldPreventDefault,
      shouldStopPropagation,
      transform = transform.andThen(s => new EventStreamOps(s).withCurrentValueOfC(signal))
    )

  def sample[B](signal: Signal[B]): EventPropToStream[Ev, B] =
    new EventPropToStream(
      key,
      shouldUseCapture,
      shouldPreventDefault,
      shouldStopPropagation,
      transform = transform.andThen(_.sample(signal))
    )

  // @TODO[API] print with dom.console.log automatically only if a JS value detected? Not sure if possible to do well.

  /**
   * print events using println - use for Scala values
   */
  def debugLog(prefix: String = "event", when: A => Boolean = _ => true): EventPropToStream[Ev, A] =
    new EventPropToStream(
      key,
      shouldUseCapture,
      shouldPreventDefault,
      shouldStopPropagation,
      transform = transform.andThen(_.debugLog(prefix, when))
    )

  /**
   * print events using dom.console.log - use for JS values
   */
  def debugLogJs(prefix: String = "event", when: A => Boolean = _ => true): EventPropToStream[Ev, A] =
    new EventPropToStream(
      key,
      shouldUseCapture,
      shouldPreventDefault,
      shouldStopPropagation,
      transform = transform.andThen(_.debugLogJs(prefix, when))
    )

  def debugBreak(when: A => Boolean = _ => true): EventPropToStream[Ev, A] =
    new EventPropToStream(
      key,
      shouldUseCapture,
      shouldPreventDefault,
      shouldStopPropagation,
      transform = transform.andThen(_.debugBreak(when))
    )

  def debugSpy(fn: A => Unit): EventPropToStream[Ev, A] =
    new EventPropToStream(
      key,
      shouldUseCapture,
      shouldPreventDefault,
      shouldStopPropagation,
      transform = transform.andThen(_.debugSpy(fn))
    )

}
