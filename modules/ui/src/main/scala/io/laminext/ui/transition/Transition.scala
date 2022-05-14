package io.laminext.ui
package transition

import com.raquo.laminar.api.L._
import io.laminext.syntax.core._
import com.raquo.laminar.nodes.ReactiveHtmlElement
import scala.scalajs.js

object Transition {

  def apply(
    show: Signal[Boolean],
    config: TransitionConfig,
    observer: Observer[Boolean] = Observer.empty
  ): Modifier[HtmlElement] = {
    val classes = Var[Seq[(Seq[String], Boolean)]](Seq.empty)
    val bus     = new EventBus[TransitionEvent]

    import TransitionEvent._

    val enterFromClasses =
      Seq(
        config.hidden        -> false,
        config.nonHidden     -> true,
        config.showing       -> false,
        config.inTransition  -> true,
        config.enter         -> true,
        config.enterDuration -> true,
        config.enterTiming   -> true,
        config.enterFrom     -> true,
        config.enterTo       -> false,
        config.leave         -> false,
        config.leaveDuration -> false,
        config.leaveTiming   -> false,
        config.leaveFrom     -> false,
        config.leaveTo       -> false
      )

    val enterToClasses =
      Seq(
        config.nonHidden     -> true,
        config.hidden        -> false,
        config.showing       -> false,
        config.inTransition  -> true,
        config.enter         -> true,
        config.enterDuration -> true,
        config.enterTiming   -> true,
        config.enterFrom     -> false,
        config.enterTo       -> true,
        config.leave         -> false,
        config.leaveDuration -> false,
        config.leaveTiming   -> false,
        config.leaveFrom     -> false,
        config.leaveTo       -> false
      )

    val leaveFromClasses =
      Seq(
        config.nonHidden     -> true,
        config.hidden        -> false,
        config.showing       -> false,
        config.inTransition  -> true,
        config.enter         -> false,
        config.enterDuration -> false,
        config.enterTiming   -> false,
        config.enterFrom     -> false,
        config.enterTo       -> false,
        config.leave         -> true,
        config.leaveDuration -> true,
        config.leaveTiming   -> true,
        config.leaveFrom     -> true,
        config.leaveTo       -> false
      )

    val leaveToClasses =
      Seq(
        config.nonHidden     -> true,
        config.hidden        -> false,
        config.showing       -> false,
        config.inTransition  -> true,
        config.enter         -> false,
        config.enterDuration -> false,
        config.enterTiming   -> false,
        config.enterFrom     -> false,
        config.enterTo       -> false,
        config.leave         -> true,
        config.leaveDuration -> true,
        config.leaveTiming   -> true,
        config.leaveFrom     -> false,
        config.leaveTo       -> true
      )

    def resetClasses(show: Boolean) =
      Seq(
        config.hidden        -> !show,
        config.nonHidden     -> show,
        config.showing       -> show,
        config.inTransition  -> false,
        config.enter         -> false,
        config.enterDuration -> false,
        config.enterTiming   -> false,
        config.enterFrom     -> false,
        config.enterTo       -> false,
        config.leave         -> false,
        config.leaveDuration -> false,
        config.leaveTiming   -> false,
        config.leaveFrom     -> false,
        config.leaveTo       -> false
      )

    def scheduleEvent = (event: TransitionEvent) => {
      val _ = js.timers.setTimeout(0) {
        bus.writer.onNext(event)
      }
    }

    Seq(
      smartClass(classes.signal),
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
