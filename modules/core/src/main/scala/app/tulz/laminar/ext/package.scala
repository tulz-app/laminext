package app.tulz.laminar

import app.tulz.laminext._
import app.tulz.laminext.ops.boolean.BooleanOps
import app.tulz.laminext.ops.element.InputElementOps
import app.tulz.laminext.ops.element.TextAreaElementOps
import app.tulz.laminext.ops.eventproptransformation.EventPropTransformationOps
import app.tulz.laminext.ops.future.FutureCompanionOps
import app.tulz.laminext.ops.future.FutureOfEitherOps
import app.tulz.laminext.ops.future.FutureOps
import app.tulz.laminext.ops.htmlelement.ReactiveHtmlElementOps
import app.tulz.laminext.ops.htmltag.HtmlTagOps
import app.tulz.laminext.ops.iterable.SequenceOps
import app.tulz.laminext.ops.observable.ObservableOfBooleanOps
import app.tulz.laminext.ops.option.OptionOfSignalOps
import app.tulz.laminext.ops.option.OptionOps
import app.tulz.laminext.ops.reactiveeventprop.ReactiveEventPropOps
import app.tulz.laminext.ops.signal._
import app.tulz.laminext.ops.stream.EventStreamCompanionOps
import app.tulz.laminext.ops.stream.EventStreamOfEitherOps
import app.tulz.laminext.ops.stream.EventStreamOfOptionOps
import app.tulz.laminext.ops.stream.EventStreamOfUnitOps
import app.tulz.laminext.ops.stream.EventStreamOps
import app.tulz.laminext.ops.string.StringOps
import app.tulz.laminext.ops.svgelement.ReactiveSvgElementOps
import app.tulz.laminext.ops.svgtag.SvgTagOps
import app.tulz.laminext.util.ClassJoin
import app.tulz.laminext.util.ClassTokenize
import app.tulz.laminext.util.SmartClass
import com.raquo.airstream.core.Observable
import com.raquo.airstream.core.Observer
import com.raquo.airstream.eventbus.EventBus
import com.raquo.airstream.eventstream.EventStream
import com.raquo.airstream.signal.Signal
import com.raquo.domtypes.generic.Modifier
import com.raquo.laminar.api.L._
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.builders.SvgTag
import com.raquo.laminar.emitter.EventPropTransformation
import com.raquo.laminar.keys.ReactiveEventProp
import com.raquo.laminar.nodes._
import org.scalajs.dom

import scala.collection.generic.IsSeq
import scala.concurrent.Future

package object ext extends HtmlEntities with SmartClass with ClassJoin with ClassTokenize with ExtraEvents {

  implicit def syntaxSignalCompanion(s: Signal.type): SignalCompanionOps.type = SignalCompanionOps

  implicit def syntaxEventStreamCompanion(e: EventStream.type): EventStreamCompanionOps.type = EventStreamCompanionOps

  implicit def syntaxFuture[A](f: => Future[A]): FutureOps[A] = new FutureOps[A](f)

  implicit def syntaxFutureCompanion(f: Future.type): FutureCompanionOps.type = FutureCompanionOps

  implicit def syntaxFutureOfEither[A, B](f: => Future[Either[A, B]]): FutureOfEitherOps[A, B] = new FutureOfEitherOps[A, B](f)

  implicit def syntaxSignal[A](s: Signal[A]): SignalOps[A] = new SignalOps[A](s)

  implicit def syntaxVarOfBoolean(v: Var[Boolean]): VarOfBooleanOps = new VarOfBooleanOps(v)

  implicit def syntaxEventStreamOfUnit(s: EventStream[Unit]): EventStreamOfUnitOps = new EventStreamOfUnitOps(s)

  implicit def syntaxEventStreamOfEither[A, B](s: EventStream[Either[A, B]]): EventStreamOfEitherOps[A, B] = new EventStreamOfEitherOps[A, B](s)

  implicit def syntaxEventStreamOfOption[A](s: EventStream[Option[A]]): EventStreamOfOptionOps[A] = new EventStreamOfOptionOps[A](s)

  implicit def syntaxSignalOfEither[A, B](s: Signal[Either[A, B]]): SignalOfEitherOps[A, B] = new SignalOfEitherOps[A, B](s)

  implicit def syntaxSignalOfOption[A](s: Signal[Option[A]]): SignalOfOptionOps[A] = new SignalOfOptionOps[A](s)

  implicit def syntaxSignalOfOptionOfSignal[A](s: Signal[Option[Signal[A]]]): SignalOfOptionOfSignalOps[A] = new SignalOfOptionOfSignalOps[A](s)

  implicit def syntaxSignalOfBoolean(s: Signal[Boolean]): SignalOfBooleanOps = new SignalOfBooleanOps(s)

  implicit def syntaxObservableOfBooleanOps(o: Observable[Boolean]): ObservableOfBooleanOps = new ObservableOfBooleanOps(o)

  implicit def syntaxEventStream[A](s: EventStream[A]): EventStreamOps[A] = new EventStreamOps[A](s)

  implicit def syntaxHtmlTag[T <: dom.html.Element](tag: HtmlTag[T]): HtmlTagOps[T] = new HtmlTagOps[T](tag)

  implicit def syntaxSvgTag[T <: dom.svg.Element](tag: SvgTag[T]): SvgTagOps[T] = new SvgTagOps[T](tag)

  implicit def syntaxReactiveHtmlElement[T <: dom.html.Element](el: ReactiveHtmlElement[T]): ReactiveHtmlElementOps[T] = new ReactiveHtmlElementOps[T](el)

  implicit def syntaxReactiveSvgElement[T <: dom.svg.Element](el: ReactiveSvgElement[T]): ReactiveSvgElementOps[T] = new ReactiveSvgElementOps[T](el)

  implicit def syntaxString(s: String): StringOps = new StringOps(s)

  implicit def syntaxBoolean(b: Boolean): BooleanOps = new BooleanOps(b)

  implicit def syntaxOption[A](o: Option[A]): OptionOps[A] = new OptionOps[A](o)

  implicit def syntaxOptionOfSignal[A](o: Option[Signal[A]]): OptionOfSignalOps[A] = new OptionOfSignalOps[A](o)

  implicit def syntaxSequence[Repr](coll: Repr)(implicit seq: IsSeq[Repr]): SequenceOps[Repr, seq.type] = new SequenceOps(coll, seq)

  implicit def syntaxInputElement(el: Input): InputElementOps = new InputElementOps(el)

  implicit def syntaxTextAreaElement(el: TextArea): TextAreaElementOps = new TextAreaElementOps(el)

  implicit def syntaxEventPropTransformation[Ev <: dom.Event, V](underlying: EventPropTransformation[Ev, V]): EventPropTransformationOps[Ev, V] =
    new EventPropTransformationOps[Ev, V](underlying)

  implicit def syntaxReactiveEventProp[Ev <: dom.Event](underlying: ReactiveEventProp[Ev]): ReactiveEventPropOps[Ev] =
    new ReactiveEventPropOps[Ev](underlying)

  val futureChild: FutureChildReceiver.type = FutureChildReceiver

  val futureChildren: FutureChildrenReceiver.type = FutureChildrenReceiver

  val unsafeInnerHtml: UnsafeInnerHtmlReceiver.type = UnsafeInnerHtmlReceiver

  @inline def unsafeAppendRawChild[El <: Element](child: org.scalajs.dom.raw.Node): Modifier[El] = new UnsafeAppendRawChildModifier[El](child)

  @inline def nodeSeq[El <: ReactiveElement[dom.Element]](seq: Modifier[El]*): Modifier[El] = new NodeSeqModifier[El](seq)

  @inline def nodeSequence[A, El <: ReactiveElement[dom.Element]](seq: Seq[Modifier[El]]): Modifier[El] = nodeSeq(seq: _*)

  @inline def whenDefined[A, El <: Element](o: Option[A])(mods: Modifier[El]*): Modifier[El] = o.whenDefined(mods: _*)

  @inline def when[A, El <: Element](b: Boolean)(mods: Modifier[El]*): Modifier[El] = b.whenTrue(mods: _*)

  @inline def whenEmpty[A, El <: Element](o: Option[A])(mods: Modifier[El]*): Modifier[El] = o.whenEmpty(mods: _*)

  @inline def whenNot[A, El <: Element](b: Boolean)(mods: Modifier[El]*): Modifier[El] = b.whenFalse(mods: _*)

  @inline def tee[T](observers: Observer[T]*): Observer[T] = new TeeObserver[T](observers)

  def withBus[T](body: Observer[T] => Unit): EventStream[T] = {
    val bus = new EventBus[T]
    body(bus.writer)
    bus.events
  }

  def createTrigger(): (EventStream[Unit], () => Unit) = {
    val bus = new EventBus[Unit]()
    (bus.events, () => { bus.writer.onNext((): Unit) })
  }

  def storedBoolean(name: String, initial: Boolean = true): StoredBoolean = new StoredBoolean(name, initial)

  def storedString(name: String, initial: String): StoredString = new StoredString(name, initial)

}
