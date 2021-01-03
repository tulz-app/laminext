package io.laminext
package domtypes

import com.raquo.laminar.api.L.EventProp
import com.raquo.domtypes.jsdom.defs.events.TypedTargetEvent
import com.raquo.laminar.keys.ReactiveEventProp
import org.scalajs.dom

trait ExtraEvents {

  val onAnimationStart: ReactiveEventProp[TypedTargetEvent[dom.Element]] = new EventProp("animationstart")

  val onAnimationIteration: ReactiveEventProp[TypedTargetEvent[dom.Element]] = new EventProp("animationiteration")

  val onAnimationCancel: ReactiveEventProp[TypedTargetEvent[dom.Element]] = new EventProp("animationcancel")

  val onAnimationEnd: ReactiveEventProp[TypedTargetEvent[dom.Element]] = new EventProp("animationend")

  val onTransitionRun: ReactiveEventProp[TypedTargetEvent[dom.Element]] = new EventProp("transitionrun")

  val onTransitionStart: ReactiveEventProp[TypedTargetEvent[dom.Element]] = new EventProp("transitionstart")

  val onTransitionCancel: ReactiveEventProp[TypedTargetEvent[dom.Element]] = new EventProp("transitioncancel")

  val onTransitionEnd: ReactiveEventProp[TypedTargetEvent[dom.Element]] = new EventProp("transitionend")

}
