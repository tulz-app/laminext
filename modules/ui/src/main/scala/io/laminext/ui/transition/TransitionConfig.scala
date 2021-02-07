package io.laminext.ui.transition

import org.scalajs.dom

/**
 * @param hidden when hidden
 * @param nonHidden when showing or in transition
 * @param showing when showing
 * @param inTransition when in transition
 * @param enter during enter transition
 * @param enterDuration enter duriation
 * @param enterTiming enter timing
 * @param enterFrom enter from
 * @param enterTo enter to
 * @param leave during leave transition
 * @param leaveDuration leave duration
 * @param leaveTiming leave timing
 * @param leaveFrom leave from
 * @param leaveTo leave to
 */
case class TransitionConfig(
  hidden: Seq[String],
  nonHidden: Seq[String],
  showing: Seq[String],
  inTransition: Seq[String],
  enter: Seq[String],
  enterDuration: Seq[String],
  enterTiming: Seq[String],
  enterFrom: Seq[String],
  enterTo: Seq[String],
  leave: Seq[String],
  leaveDuration: Seq[String],
  leaveTiming: Seq[String],
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
    enterDuration: Seq[String] => Seq[String] = identity,
    enterTiming: Seq[String] => Seq[String] = identity,
    enterFrom: Seq[String] => Seq[String] = identity,
    enterTo: Seq[String] => Seq[String] = identity,
    leave: Seq[String] => Seq[String] = identity,
    leaveDuration: Seq[String] => Seq[String] = identity,
    leaveTiming: Seq[String] => Seq[String] = identity,
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
    enterDuration = enterDuration(this.enterDuration),
    enterTiming = enterTiming(this.enterTiming),
    enterFrom = enterFrom(this.enterFrom),
    enterTo = enterTo(this.enterTo),
    leave = leave(this.leave),
    leaveDuration = leaveDuration(this.leaveDuration),
    leaveTiming = leaveTiming(this.leaveTiming),
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

  val empty: TransitionConfig = TransitionConfig(
    inTransition = "",
    enterDuration = "",
    enterTiming = "",
    enterFrom = "",
    enterTo = "",
    leaveDuration = "",
    leaveTiming = "",
    leaveFrom = "",
    leaveTo = "",
  )

  def apply(
    hidden: String = "hidden",
    nonHidden: String = "",
    showing: String = "",
    inTransition: String,
    enter: String = "",
    enterDuration: String,
    enterTiming: String,
    enterFrom: String,
    enterTo: String,
    leave: String = "",
    leaveDuration: String,
    leaveTiming: String,
    leaveFrom: String,
    leaveTo: String,
    onEnterFrom: dom.html.Element => Unit = _ => {},
    onEnterTo: dom.html.Element => Unit = _ => {},
    onLeaveFrom: dom.html.Element => Unit = _ => {},
    onLeaveTo: dom.html.Element => Unit = _ => {},
    onReset: (dom.html.Element, Boolean) => Unit = (_, _) => {},
  ): TransitionConfig = TransitionConfig(
    hidden = hidden.split(' ').toSeq.map(_.trim).filterNot(_.isEmpty),
    nonHidden = nonHidden.split(' ').toSeq.map(_.trim).filterNot(_.isEmpty),
    showing = showing.split(' ').toSeq.map(_.trim).filterNot(_.isEmpty),
    inTransition = inTransition.split(' ').toSeq.map(_.trim).filterNot(_.isEmpty),
    enter = enter.split(' ').toSeq.map(_.trim).filterNot(_.isEmpty),
    enterDuration = enterDuration.split(' ').toSeq.map(_.trim).filterNot(_.isEmpty),
    enterTiming = enterTiming.split(' ').toSeq.map(_.trim).filterNot(_.isEmpty),
    enterFrom = enterFrom.split(' ').toSeq.map(_.trim).filterNot(_.isEmpty),
    enterTo = enterTo.split(' ').toSeq.map(_.trim).filterNot(_.isEmpty),
    leave = leave.split(' ').toSeq.map(_.trim).filterNot(_.isEmpty),
    leaveDuration = leaveDuration.split(' ').toSeq.map(_.trim).filterNot(_.isEmpty),
    leaveTiming = leaveTiming.split(' ').toSeq.map(_.trim).filterNot(_.isEmpty),
    leaveFrom = leaveFrom.split(' ').toSeq.map(_.trim).filterNot(_.isEmpty),
    leaveTo = leaveTo.split(' ').toSeq.map(_.trim).filterNot(_.isEmpty),
    onEnterFrom = onEnterFrom,
    onEnterTo = onEnterTo,
    onLeaveFrom = onLeaveFrom,
    onLeaveTo = onLeaveTo,
    onReset = onReset
  )

}
