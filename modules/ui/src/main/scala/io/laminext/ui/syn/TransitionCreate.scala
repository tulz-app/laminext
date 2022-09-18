package io.laminext.ui.syn

import com.raquo.laminar.api.L._
import io.laminext.syntax.core._
import io.laminext.ui.TransitionEvent
import io.laminext.ui.theme.TransitionConfig

import scala.scalajs.js

trait TransitionCreate {

  private def concatClasses(seq: (Seq[String], Boolean)*): Seq[(String, Boolean)] =
    seq.flatMap { case (classes, enable) =>
      classes.map(_ -> enable)
    }

  def addTransition(
    show: Signal[Boolean],
    config: TransitionConfig,
    observer: Observer[Boolean] = Observer.empty
  ): Modifier[HtmlElement] = {
    val classes = Var[Seq[(String, Boolean)]](Seq.empty)
    val bus     = new EventBus[TransitionEvent]()

    import TransitionEvent._

    val enterFromClasses =
      concatClasses(
        config.hidden       -> false,
        config.nonHidden    -> true,
        config.showing      -> false,
        config.inTransition -> true,
        config.enter        -> true,
        config.enterFrom    -> true,
        config.enterTo      -> false,
        config.leave        -> false,
        config.leaveFrom    -> false,
        config.leaveTo      -> false
      )

    val enterToClasses =
      concatClasses(
        config.nonHidden    -> true,
        config.hidden       -> false,
        config.showing      -> false,
        config.inTransition -> true,
        config.enter        -> true,
        config.enterFrom    -> false,
        config.enterTo      -> true,
        config.leave        -> false,
        config.leaveFrom    -> false,
        config.leaveTo      -> false
      )

    val leaveFromClasses =
      concatClasses(
        config.nonHidden    -> true,
        config.hidden       -> false,
        config.showing      -> false,
        config.inTransition -> true,
        config.enter        -> false,
        config.enterFrom    -> false,
        config.enterTo      -> false,
        config.leave        -> true,
        config.leaveFrom    -> true,
        config.leaveTo      -> false
      )

    val leaveToClasses =
      concatClasses(
        config.nonHidden    -> true,
        config.hidden       -> false,
        config.showing      -> false,
        config.inTransition -> true,
        config.enter        -> false,
        config.enterFrom    -> false,
        config.enterTo      -> false,
        config.leave        -> true,
        config.leaveFrom    -> false,
        config.leaveTo      -> true
      )

    def resetClasses(show: Boolean) =
      concatClasses(
        config.hidden       -> !show,
        config.nonHidden    -> show,
        config.showing      -> show,
        config.inTransition -> false,
        config.enter        -> false,
        config.enterFrom    -> false,
        config.enterTo      -> false,
        config.leave        -> false,
        config.leaveFrom    -> false,
        config.leaveTo      -> false
      )

    def scheduleEvent = (event: TransitionEvent) => {
      val _ = js.timers.setTimeout(0) {
        bus.writer.onNext(event)
      }
    }

    Seq(
      cls <-- classes.signal,
      onMountCallback { (_: MountContext[_]) =>
        bus.writer.onNext(Reset)
      },
      onTransitionEnd --> { _ =>
        scheduleEvent(Reset)
      },
      inContext { (el: HtmlElement) =>
        bus.events.withCurrentValueOf(show) --> {
          case (EnterFrom, _)     =>
            config.onEnterFrom(el.ref)
            classes.writer.onNext(enterFromClasses)
            scheduleEvent(EnterTo)
          case (EnterTo, _)       =>
            config.onEnterTo(el.ref)
            classes.writer.onNext(enterToClasses)
          case (LeaveFrom, _)     =>
            config.onLeaveFrom(el.ref)
            classes.writer.onNext(leaveFromClasses)
            scheduleEvent(LeaveTo)
          case (LeaveTo, _)       =>
            config.onLeaveTo(el.ref)
            classes.writer.onNext(leaveToClasses)
          case (Reset, show)      =>
            classes.writer.onNext(resetClasses(show))
            scheduleEvent(AfterReset)
          case (AfterReset, show) =>
            config.onReset(el.ref, show)
            observer.onNext(show)
        }
      },
      show.transitions.map {
        case (Some(_), true)  => EnterFrom
        case (Some(_), false) => LeaveFrom
        case (None, _)        => Reset
      } --> bus.writer
    )
  }

}
