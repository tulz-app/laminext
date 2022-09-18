package io.laminext.ui.theme

import org.scalajs.dom

/**
 * @param hidden
 *   when hidden
 * @param nonHidden
 *   when showing or in transition
 * @param showing
 *   when showing
 * @param inTransition
 *   when in transition
 * @param enter
 *   during enter transition
 * @param enterFrom
 *   enter from
 * @param enterTo
 *   enter to
 * @param leave
 *   during leave transition
 * @param leaveFrom
 *   leave from
 * @param leaveTo
 *   leave to
 * @param onEnterFrom
 *   optional callback to set additional enterFrom settings of the element
 * @param onEnterTo
 *   optional callback to set additional enterTo settings of the element
 * @param onLeaveFrom
 *   optional callback to set additional leaveFrom settings of the element
 * @param onLeaveTo
 *   optional callback to set additional leaveFrom settings of the element
 * @param onReset
 *   optional callback to reset the element
 */
final case class TransitionConfig(
  hidden: String,
  nonHidden: String,
  showing: String,
  inTransition: String,
  enter: String,
  enterFrom: String,
  enterTo: String,
  leave: String,
  leaveFrom: String,
  leaveTo: String,
  onEnterFrom: dom.html.Element => Unit,
  onEnterTo: dom.html.Element => Unit,
  onLeaveFrom: dom.html.Element => Unit,
  onLeaveTo: dom.html.Element => Unit,
  onReset: (dom.html.Element, Boolean) => Unit,
) {

  def customize(
    hidden: String => String = identity,
    nonHidden: String => String = identity,
    showing: String => String = identity,
    inTransition: String => String = identity,
    enter: String => String = identity,
    enterFrom: String => String = identity,
    enterTo: String => String = identity,
    leave: String => String = identity,
    leaveFrom: String => String = identity,
    leaveTo: String => String = identity,
    onEnterFrom: dom.html.Element => Unit = this.onEnterFrom,
    onEnterTo: dom.html.Element => Unit = this.onEnterTo,
    onLeaveFrom: dom.html.Element => Unit = this.onLeaveFrom,
    onLeaveTo: dom.html.Element => Unit = this.onLeaveTo,
    onReset: (dom.html.Element, Boolean) => Unit = this.onReset,
  ): TransitionConfig = new TransitionConfig(
    hidden = hidden(this.hidden),
    nonHidden = nonHidden(this.nonHidden),
    showing = showing(this.showing),
    inTransition = inTransition(this.inTransition),
    enter = enter(this.enter),
    enterFrom = enterFrom(this.enterFrom),
    enterTo = enterTo(this.enterTo),
    leave = leave(this.leave),
    leaveFrom = leaveFrom(this.leaveFrom),
    leaveTo = leaveTo(this.leaveTo),
    onEnterFrom = onEnterFrom,
    onEnterTo = onEnterTo,
    onLeaveFrom = onLeaveFrom,
    onLeaveTo = onLeaveTo,
    onReset = onReset
  )

}

object TransitionConfig {

  val empty: TransitionConfig = TransitionConfig()

  def apply(
    hidden: String = "",
    nonHidden: String = "",
    showing: String = "",
    inTransition: String = "",
    enter: String = "",
    enterFrom: String = "",
    enterTo: String = "",
    leave: String = "",
    leaveFrom: String = "",
    leaveTo: String = "",
    onEnterFrom: dom.html.Element => Unit = _ => {},
    onEnterTo: dom.html.Element => Unit = _ => {},
    onLeaveFrom: dom.html.Element => Unit = _ => {},
    onLeaveTo: dom.html.Element => Unit = _ => {},
    onReset: (dom.html.Element, Boolean) => Unit = (_, _) => {},
  ): TransitionConfig = new TransitionConfig(
    hidden = hidden,
    nonHidden = nonHidden,
    showing = showing,
    inTransition = inTransition,
    enter = enter,
    enterFrom = enterFrom,
    enterTo = enterTo,
    leave = leave,
    leaveFrom = leaveFrom,
    leaveTo = leaveTo,
    onEnterFrom = onEnterFrom,
    onEnterTo = onEnterTo,
    onLeaveFrom = onLeaveFrom,
    onLeaveTo = onLeaveTo,
    onReset = onReset
  )

}
