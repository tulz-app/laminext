package app.tulz.laminext.internal

import com.raquo.laminar.api.L._
import com.raquo.airstream.eventbus.EventBus
import com.raquo.laminar.keys.ReactiveEventProp
import org.scalajs.dom

import scala.scalajs.js

private[tulz] object Helpers {

  def eventCallback[Ev <: dom.Event](
    bus: EventBus[Ev],
    key: ReactiveEventProp[Ev],
    shouldPreventDefault: Boolean,
    shouldStopPropagation: Boolean
  ): Ev => Unit =
    (ev: Ev) => {
      if (shouldStopPropagation) {
        ev.stopPropagation()
      }
      if (shouldPreventDefault) {
        ev.preventDefault()
      }

      if (
        ev.defaultPrevented
        && key == onClick
        && ev.target.asInstanceOf[dom.Element].tagName == "INPUT" // ugly but performy
        && ev.target.asInstanceOf[dom.html.Input].`type` == "checkbox"
      ) {
        // Special case: See README and/or https://stackoverflow.com/a/32710212/2601788
        // @TODO[API] Should this behaviour extend to all checkbox.onClick events by default?
        val _ = js.timers.setTimeout(0) {
          bus.writer.onNext(ev)
        }
      } else {
        bus.writer.onNext(ev)
      }
    }

}
