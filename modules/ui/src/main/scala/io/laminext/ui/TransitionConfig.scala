package io.laminext.ui

import org.scalajs.dom

/**
 * @param hidden when hidden
 * @param nonHidden when showing or in transition
 * @param showing when showing
 * @param inTransition when in transition
 * @param enter during enter transition
 * @param enterFrom enter from
 * @param enterTo enter to
 * @param leave during leave transition
 * @param leaveFrom leave from
 * @param leaveTo leave to
 * @param onEnterFrom optional callback to set additional enterFrom settings of the element
 * @param onEnterTo optional callback to set additional enterTo settings of the element
 * @param onLeaveFrom optional callback to set additional leaveFrom settings of the element
 * @param onLeaveTo optional callback to set additional leaveFrom settings of the element
 * @param onReset optional callback to reset the element
 */
final case class TransitionConfig(
  hidden: Seq[String],
  nonHidden: Seq[String],
  showing: Seq[String],
  inTransition: Seq[String],
  enter: Seq[String],
  enterFrom: Seq[String],
  enterTo: Seq[String],
  leave: Seq[String],
  leaveFrom: Seq[String],
  leaveTo: Seq[String],
  onEnterFrom: dom.html.Element => Unit,
  onEnterTo: dom.html.Element => Unit,
  onLeaveFrom: dom.html.Element => Unit,
  onLeaveTo: dom.html.Element => Unit,
  onReset: (dom.html.Element, Boolean) => Unit,
) {

  def customize(
    hidden: Seq[String] => Seq[String] = identity,
    nonHidden: Seq[String] => Seq[String] = identity,
    showing: Seq[String] => Seq[String] = identity,
    inTransition: Seq[String] => Seq[String] = identity,
    enter: Seq[String] => Seq[String] = identity,
    enterFrom: Seq[String] => Seq[String] = identity,
    enterTo: Seq[String] => Seq[String] = identity,
    leave: Seq[String] => Seq[String] = identity,
    leaveFrom: Seq[String] => Seq[String] = identity,
    leaveTo: Seq[String] => Seq[String] = identity,
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

  def split(s: String): Seq[String] =
    s.split(' ').toSeq.map(_.trim).filterNot(_.isEmpty)

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
    hidden = split(hidden),
    nonHidden = split(nonHidden),
    showing = split(showing),
    inTransition = split(inTransition),
    enter = split(enter),
    enterFrom = split(enterFrom),
    enterTo = split(enterTo),
    leave = split(leave),
    leaveFrom = split(leaveFrom),
    leaveTo = split(leaveTo),
    onEnterFrom = onEnterFrom,
    onEnterTo = onEnterTo,
    onLeaveFrom = onLeaveFrom,
    onLeaveTo = onLeaveTo,
    onReset = onReset
  )

}
