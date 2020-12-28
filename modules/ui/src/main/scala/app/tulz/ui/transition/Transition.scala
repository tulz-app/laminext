package app.tulz.ui
package transition

import com.raquo.laminar.api.L._
import app.tulz.laminar.ext.onTransitionCancel
import app.tulz.laminar.ext.onTransitionEnd
import app.tulz.laminar.ext.smartClass
import com.raquo.laminar.nodes.ReactiveHtmlElement

import scala.scalajs.js

object Transition {

  def apply(
    show: Signal[Boolean],
    config: TransitionConfig
  ): Modifier[ReactiveHtmlElement.Base] = {
    val classes = Var[Seq[(Seq[String], Boolean)]](Seq.empty)
    val bus     = new EventBus[TransitionEvent]()

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
      show.map { toShow => if (toShow) EnterFrom else LeaveFrom } --> bus.writer,
      onMountCallback { _: MountContext[_] =>
        bus.writer.onNext(Reset)
      },
      onTransitionEnd --> { _ =>
        scheduleEvent(Reset)
      },
      onTransitionCancel --> { _ =>
        scheduleEvent(Reset)
      },
      bus.events.withCurrentValueOf(show) --> {
        case (EnterFrom, _) =>
          classes.writer.onNext(enterFromClasses)
          scheduleEvent(EnterTo)
        case (EnterTo, _) =>
          classes.writer.onNext(enterToClasses)
        case (LeaveFrom, _) =>
          classes.writer.onNext(leaveFromClasses)
          scheduleEvent(LeaveTo)
        case (LeaveTo, _) =>
          classes.writer.onNext(leaveToClasses)
        case (Reset, show) =>
          classes.writer.onNext(resetClasses(show))
      }
    )
  }

}
