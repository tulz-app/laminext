package app.tulz.ui.transition

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
        config.hiddenClasses    -> false,
        config.enterClasses     -> true,
        config.enterFromClasses -> true,
        config.enterToClasses   -> false,
        config.leaveClasses     -> false,
        config.leaveFromClasses -> false,
        config.leaveToClasses   -> false
      )

    val enterToClasses =
      Seq(
        config.hiddenClasses    -> false,
        config.enterClasses     -> true,
        config.enterFromClasses -> false,
        config.enterToClasses   -> true,
        config.leaveClasses     -> false,
        config.leaveFromClasses -> false,
        config.leaveToClasses   -> false
      )

    val leaveFromClasses =
      Seq(
        config.hiddenClasses    -> false,
        config.enterClasses     -> false,
        config.enterFromClasses -> false,
        config.enterToClasses   -> false,
        config.leaveClasses     -> true,
        config.leaveFromClasses -> true,
        config.leaveToClasses   -> false
      )

    val leaveToClasses =
      Seq(
        config.hiddenClasses    -> false,
        config.enterClasses     -> false,
        config.enterFromClasses -> false,
        config.enterToClasses   -> false,
        config.leaveClasses     -> true,
        config.leaveFromClasses -> false,
        config.leaveToClasses   -> true
      )

    def resetClasses(show: Boolean) =
      Seq(
        config.hiddenClasses    -> !show,
        config.enterClasses     -> false,
        config.enterFromClasses -> false,
        config.enterToClasses   -> false,
        config.leaveClasses     -> false,
        config.leaveFromClasses -> false,
        config.leaveToClasses   -> false
      )

    def scheduleEvent = (event: TransitionEvent) => {
      val _ = js.timers.setTimeout(0) {
        bus.writer.onNext(event)
      }
    }

    Seq(
      smartClass(classes.signal),
      show.map { toShow =>
        if (toShow) TransitionEvent.EnterFrom else TransitionEvent.LeaveFrom
      } --> bus.writer,
      onMountCallback { _: MountContext[_] =>
        bus.writer.onNext(TransitionEvent.Reset)
      },
      onTransitionEnd --> { _ =>
        scheduleEvent(TransitionEvent.Reset)
      },
      onTransitionCancel --> { _ =>
        scheduleEvent(TransitionEvent.Reset)
      },
      bus.events.withCurrentValueOf(show) --> {
        case (EnterFrom, _) =>
          classes.writer.onNext(enterFromClasses)
          scheduleEvent(TransitionEvent.EnterTo)
        case (EnterTo, _) =>
          classes.writer.onNext(enterToClasses)
        case (LeaveFrom, _) =>
          classes.writer.onNext(leaveFromClasses)
          scheduleEvent(TransitionEvent.LeaveTo)
        case (LeaveTo, _) =>
          classes.writer.onNext(leaveToClasses)
        case (Reset, show) =>
          classes.writer.onNext(resetClasses(show))
      }
    )
  }

  sealed trait TransitionEvent extends Product with Serializable

  object TransitionEvent {

    case object EnterFrom extends TransitionEvent
    case object EnterTo   extends TransitionEvent
    case object LeaveFrom extends TransitionEvent
    case object LeaveTo   extends TransitionEvent
    case object Reset     extends TransitionEvent

  }

}
