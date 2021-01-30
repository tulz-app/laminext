package io.laminext.syntax

import com.raquo.laminar.api.L._
import com.raquo.laminar.emitter.EventPropTransformation
import com.raquo.laminar.nodes.ReactiveElement
import io.laminext.core.NodeSeqModifier
import io.laminext.core.ResizeObserverBinders
import io.laminext.core.SetTimeoutBinders
import io.laminext.core.StoredBoolean
import io.laminext.core.StoredString
import io.laminext.core.TeeObserver
import io.laminext.core.ThisEventsStreamBuilder
import org.scalajs.dom

import scala.concurrent.duration.FiniteDuration

trait MiscSyntax {

  @inline def nodeSeq[El <: ReactiveElement[dom.Element]](
    seq: Modifier[El]*
  ): Modifier[El] = new NodeSeqModifier[El](seq: _*)

  @inline def nodeSequence[A, El <: ReactiveElement[dom.Element]](
    seq: Seq[Modifier[El]]
  ): Modifier[El] = nodeSeq(seq: _*)

  @inline def when[A, El <: Element](b: Boolean)(
    mods: Modifier[El]*
  ): Modifier[El] =
    if (b) { mods }
    else { emptyMod }

  @inline def whenNot[A, El <: Element](b: Boolean)(
    mods: Modifier[El]*
  ): Modifier[El] =
    if (!b) { mods }
    else { emptyMod }

  @inline def whenEmpty[El <: Element](o: Option[_])(mods: Modifier[El]*): Modifier[El] =
    when(o.isEmpty)(mods: _*)

  @inline def whenDefined[El <: Element](o: Option[_])(mods: Modifier[El]*): Modifier[El] =
    when(o.isDefined)(mods: _*)

  @inline def tee[T](observers: Observer[T]*): Observer[T] =
    new TeeObserver[T](observers)

  def buildStream[T](body: Observer[T] => Unit): EventStream[T] = {
    val bus = new EventBus[T]
    body(bus.writer)
    bus.events
  }

  @inline def createTrigger(): (EventStream[Unit], () => Unit) = {
    val (stream, callback) = EventStream.withCallback[Unit]
    (stream, () => { callback((): Unit) })
  }

  @inline def storedBoolean(name: String, initial: Boolean = true): StoredBoolean =
    new StoredBoolean(name, initial)

  @inline def storedString(name: String, initial: String): StoredString =
    new StoredString(name, initial)

  @inline def after[T](
    timeout: FiniteDuration,
    value: T,
  ): SetTimeoutBinders[T] = new SetTimeoutBinders(value, timeout)

  @inline def after(
    timeout: FiniteDuration,
  ): SetTimeoutBinders[Unit] = new SetTimeoutBinders((): Unit, timeout)

  @inline def resizeObserver: ResizeObserverBinders.type = ResizeObserverBinders

  @inline def thisEvents[Ev <: dom.Event](t: EventPropTransformation[Ev, Ev]): ThisEventsStreamBuilder[Ev, Ev] =
    new ThisEventsStreamBuilder[Ev, Ev](t, identity)

}
