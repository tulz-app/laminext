package app.tulz.fsm

import com.raquo.airstream.eventbus.EventBus
import com.raquo.airstream.eventstream.EventStream
import com.raquo.airstream.ownership.Subscription
import com.raquo.laminar.modifiers.Binder
import com.raquo.laminar.nodes.ReactiveElement
import org.scalajs.dom

class AirFSM[State](initial: State)(
  control: PartialFunction[(Option[State], State), EventStream[State]]
) {

  def binder: Binder[ReactiveElement.Base] = (element: ReactiveElement.Base) => {
    val stateChangeRequests               = new EventBus[State]()
    var currentSubscription: Subscription = null
    var currentState                      = Option.empty[State]
    ReactiveElement.bindSubscription(element) { ctx =>
      stateChangeRequests.events.foreach { nextState =>
        if (control.isDefinedAt((currentState, nextState))) {
          dom.console.debug(s"state transition: $currentState -> $nextState")
          val $nextStateChangeRequests = control((currentState, nextState))
          if (currentSubscription != null) {
            currentSubscription.kill()
          }
          currentSubscription = stateChangeRequests.writer.addSource($nextStateChangeRequests)(ctx.owner)
          currentState = Some(nextState)
        } else {
          dom.console.debug(s"unexpected state transition: $currentState -> $nextState")
        }
      }(ctx.owner)
      stateChangeRequests.writer.onNext(initial)
      new Subscription(
        ctx.owner,
        () => {
          if (currentSubscription != null) {
            currentSubscription.kill()
          }
        }
      )
    }
  }

}

object AirFSM {

  def apply[State](initial: State)(
    control: PartialFunction[(Option[State], State), EventStream[State]]
  ) = new AirFSM[State](initial)(control)

}
