package app.tulz.ui
package animation

import com.raquo.laminar.api.L._
import app.tulz.laminar.ext.onAnimationCancel
import app.tulz.laminar.ext.onAnimationEnd
import app.tulz.laminar.ext.onAnimationIteration
import com.raquo.laminar.nodes.ReactiveHtmlElement

import scala.scalajs.js

object Animation {

  def apply(
    animationClass: Signal[Option[String]],
    iterations: Int = 1,
    endObserver: Observer[String] = Observer.empty
  ): Modifier[ReactiveHtmlElement.Base] = {
    val klass            = Var[Seq[(String, Boolean)]](Seq.empty)
    var maybeLastClass   = Option.empty[String]
    val bus              = new EventBus[AnimationEvent]()
    var iterationCounter = 0

    import AnimationEvent._

    def scheduleEvent = (event: AnimationEvent) => {
      val _ = js.timers.setTimeout(0) {
        bus.writer.onNext(event)
      }
    }

    def enableClass(classToEnable: String): Unit =
      klass.update(
        _.map(_._1).distinct.filterNot(_ == classToEnable).map(_ -> false) :+ (classToEnable, true)
      )

    def disableAllClasses(): Unit =
      klass.update(_.map { case (klass, _) => (klass, false) })

    Seq(
      cls <-- klass.signal,
      animationClass.map {
        case Some(klass) => Start(klass)
        case None        => Reset
      } --> bus.writer,
      onMountCallback { _: MountContext[_] =>
        bus.writer.onNext(Reset)
      },
      onAnimationEnd --> { _ =>
        scheduleEvent(Reset)
      },
      onAnimationCancel --> { _ =>
        scheduleEvent(Reset)
      },
      onAnimationIteration --> { _ =>
        iterationCounter = iterationCounter + 1
        if (iterationCounter >= iterations) {
          scheduleEvent(Reset)
        }
      },
      bus.events --> {
        case Reset =>
          iterationCounter = 0
          disableAllClasses()
          maybeLastClass.foreach { lastClass =>
            endObserver.onNext(lastClass)
            maybeLastClass = None
          }

        case Start(nextAnimationClass) =>
          iterationCounter = 0
          maybeLastClass = Some(nextAnimationClass)
          enableClass(nextAnimationClass)
      }
    )
  }

}
