package io.laminext.fsm

import com.raquo.airstream.eventbus.EventBus
import com.raquo.airstream.eventstream.EventStream
import com.raquo.airstream.ownership.Subscription
import com.raquo.laminar.modifiers.Binder
import com.raquo.laminar.nodes.ReactiveElement
import org.scalajs.dom

class AirFSM[State](initialState: State, initialStateRequests: EventStream[State])(
  control: PartialFunction[(State, State), EventStream[State]]
) {

  def binder: Binder[ReactiveElement.Base] = (element: ReactiveElement.Base) => {
    val stateChangeRequests               = new EventBus[State]()
    var currentState                      = initialState
    var currentSubscription: Subscription = null
    ReactiveElement.bindSubscription(element) { ctx =>
      currentSubscription = stateChangeRequests.writer.addSource(initialStateRequests)(ctx.owner)
      stateChangeRequests.events.foreach { nextState =>
        if (control.isDefinedAt((currentState, nextState))) {
          val nextStateChangeRequests = control((currentState, nextState))
          if (currentSubscription != null) {
            currentSubscription.kill()
          }
          currentSubscription = stateChangeRequests.writer.addSource(nextStateChangeRequests)(ctx.owner)
          currentState = nextState
        } else {
          dom.console.debug(s"unhandled state transition: $currentState -> $nextState")
        }
      }(ctx.owner)
      stateChangeRequests.writer.onNext(initialState)
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

  def apply[State](initialState: State, initialStateRequests: EventStream[State])(
    control: PartialFunction[(State, State), EventStream[State]]
  ) = new AirFSM[State](initialState, initialStateRequests)(control)

}
