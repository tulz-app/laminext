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
import app.tulz.laminext.ops.option.OptionOps
import app.tulz.laminext.ops.reactiveeventprop.ReactiveEventPropOps
import app.tulz.laminext.ops.signal._
import app.tulz.laminext.ops.stream.EventStreamOfEitherOps
import app.tulz.laminext.ops.stream.EventStreamOfOptionOps
import app.tulz.laminext.ops.stream.EventStreamOfUnitOps
import app.tulz.laminext.ops.stream.EventStreamOps
import app.tulz.laminext.ops.string.StringOps
import app.tulz.laminext.ops.svgelement.ReactiveSvgElementOps
import app.tulz.laminext.ops.svgtag.SvgTagOps
import com.raquo.airstream.core.Observable
import com.raquo.airstream.core.Observer
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

package object ext extends SimpleUtilities with HtmlEntities {

  implicit def syntaxSignalCompanion(s: Signal.type): SignalCompanionOps = new SignalCompanionOps

  implicit def syntaxFuture[A](f: => Future[A]): FutureOps[A] = new FutureOps[A](f)

  implicit def syntaxFutureCompanion(f: Future.type): FutureCompanionOps.type = FutureCompanionOps

  implicit def syntaxFutureOfEither[A, B](f: => Future[Either[A, B]]): FutureOfEitherOps[A, B] = new FutureOfEitherOps[A, B](f)

  implicit def syntaxSignal[A](s: Signal[A]): SignalOps[A] = new SignalOps[A](s)

  implicit def syntaxEventStreamOfUnit(s: EventStream[Unit]): EventStreamOfUnitOps = new EventStreamOfUnitOps(s)

  implicit def syntaxEventStreamOfEither[A, B](s: EventStream[Either[A, B]]): EventStreamOfEitherOps[A, B] = new EventStreamOfEitherOps[A, B](s)

  implicit def syntaxEventStreamOfOption[A](s: EventStream[Option[A]]): EventStreamOfOptionOps[A] = new EventStreamOfOptionOps[A](s)

  implicit def syntaxSignalOfEither[A, B](s: Signal[Either[A, B]]): SignalOfEitherOps[A, B] = new SignalOfEitherOps[A, B](s)

  implicit def syntaxSignalOfOption[A](s: Signal[Option[A]]): SignalOfOptionOps[A] = new SignalOfOptionOps[A](s)

  implicit def syntaxSignalOfOptionOfSignal[A](s: Signal[Option[Signal[A]]]): SignalOfOptionOfSignalOps[A] = new SignalOfOptionOfSignalOps[A](s)

  implicit def syntaxSignalOfBoolean(s: Signal[Boolean]): SignalOfBooleanOps = new SignalOfBooleanOps(s)

  implicit def syntaxObservableOfOps(o: Observable[Boolean]): ObservableOfBooleanOps = new ObservableOfBooleanOps(o)

  implicit def syntaxEventStream[A](s: EventStream[A]): EventStreamOps[A] = new EventStreamOps[A](s)

  implicit def syntaxHtmlTag[T <: dom.html.Element](tag: HtmlTag[T]): HtmlTagOps[T] = new HtmlTagOps[T](tag)

  implicit def syntaxSvgTag[T <: dom.svg.Element](tag: SvgTag[T]): SvgTagOps[T] = new SvgTagOps[T](tag)

  implicit def syntaxReactiveHtmlElement[T <: dom.html.Element](el: ReactiveHtmlElement[T]): ReactiveHtmlElementOps[T] = new ReactiveHtmlElementOps[T](el)

  implicit def syntaxReactiveSvgElement[T <: dom.svg.Element](el: ReactiveSvgElement[T]): ReactiveSvgElementOps[T] = new ReactiveSvgElementOps[T](el)

  implicit def syntaxString(s: String): StringOps = new StringOps(s)

  implicit def syntaxBoolean(b: Boolean): BooleanOps = new BooleanOps(b)

  implicit def syntaxOption[A](o: Option[A]): OptionOps[A] = new OptionOps[A](o)

  implicit def syntaxSequence[Repr](coll: Repr)(implicit seq: IsSeq[Repr]): SequenceOps[Repr, seq.type] = new SequenceOps(coll, seq)

  implicit def syntaxInputElement(el: Input): InputElementOps = new InputElementOps(el)

  implicit def syntaxTextAreaElement(el: TextArea): TextAreaElementOps = new TextAreaElementOps(el)

  implicit def syntaxEventPropTransformation[Ev <: dom.Event, V](underlying: EventPropTransformation[Ev, V]): EventPropTransformationOps[Ev, V] =
    new EventPropTransformationOps[Ev, V](underlying)

  implicit def syntaxReactiveEventProp[Ev <: dom.Event](underlying: ReactiveEventProp[Ev]): ReactiveEventPropOps[Ev] =
    new ReactiveEventPropOps[Ev](underlying)

  val futureChild: FutureChildReceiver.type = FutureChildReceiver

  val futureChildren: FutureChildrenReceiver.type = FutureChildrenReceiver

  trait AmAny
  trait AmButton extends AmAny

  def updatableSignal[A](initial: A): (Signal[A], Observer[A => A]) = UpdatableSignal(initial)

  def toggleableSignal(initial: Boolean): (Signal[Boolean], () => Unit) = ToggleableSignal(initial)

  val unsafeInnerHtml: UnsafeInnerHtmlReceiver.type = UnsafeInnerHtmlReceiver

  def unsafeAppendRawChild[El <: Element](child: org.scalajs.dom.raw.Node): Modifier[El] = new UnsafeAppendRawChildModifier[El](child)

  def noop[El <: Element]: Modifier[El] = emptyMod

  def nodeSeq[El <: ReactiveElement[dom.Element]](seq: Modifier[El]*): Modifier[El] = new NodeSeqModifier[El](seq)

  def nodeSequence[A, El <: ReactiveElement[dom.Element]](seq: Seq[Modifier[El]]): Modifier[El] = nodeSeq(seq: _*)

  @inline def renderIfEmpty[El <: Element](o: Option[_])(m: => Modifier[El]): Modifier[El] = o.renderIfEmpty(m)

  @inline def renderIfDefined[El <: Element](o: Option[_])(m: => Modifier[El]): Modifier[El] = o.renderIfDefined(m)

  def tee[T](observers: Observer[T]*): Observer[T] = new TeeObserver[T](observers)

}

//  implicit class StrictSignalOfBooleanExt(val s: StrictSignal[Boolean]) extends AnyVal {
//    def not: Signal[Boolean] = s.map(!_)
//
//    def ||(that: Signal[Boolean]): Signal[Boolean] = s.combineWith(that).map {
//      case (l, r) => l || r
//    }
//
//    def &&(that: Signal[Boolean]): Signal[Boolean] = s.combineWith(that).map {
//      case (l, r) => l && r
//    }
//  }

//  implicit class SignalOfValidatedExt[A, B](val s: Signal[Validated[A, B]]) extends AnyVal {
//    def isValid: Signal[Boolean]   = s.map(_.isValid)
//    def isInvalid: Signal[Boolean] = s.map(_.isInvalid)
//  }
//
//  implicit class SignalOfValidatedNelExt[A, B](val s: Signal[ValidatedNel[A, B]]) extends AnyVal {
//
//    def combineInvalid(other: Signal[ValidatedNel[A, B]]): Signal[ValidatedNel[A, Unit]] =
//      s.combineWith(other).map {
//        case (first, second) =>
//          first.map(_ => ()).combine(second.map(_ => ()))
//      }
//  }

//  implicit class FutureOfValidatedNelExt[E, A](val f: Future[ValidatedNel[E, A]]) extends AnyVal {
//
//    def mapValid[AA >: A, B](project: A => B)(implicit ec: ExecutionContext): Future[ValidatedNel[E, B]] = {
//      f.map(_.map(project))
//    }
//
//  }
