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
    hidden: String = null,
    nonHidden: String = null,
    showing: String = null,
    inTransition: String = null,
    enter: String = null,
    enterDuration: String = null,
    enterTiming: String = null,
    enterFrom: String = null,
    enterTo: String = null,
    leave: String = null,
    leaveDuration: String = null,
    leaveTiming: String = null,
    leaveFrom: String = null,
    leaveTo: String = null,
    onEnterFrom: dom.html.Element => Unit = this.onEnterFrom,
    onEnterTo: dom.html.Element => Unit = this.onEnterTo,
    onLeaveFrom: dom.html.Element => Unit = this.onLeaveFrom,
    onLeaveTo: dom.html.Element => Unit = this.onLeaveTo,
    onReset: (dom.html.Element, Boolean) => Unit = this.onReset,
  ): TransitionConfig = new TransitionConfig(
    hidden = if (hidden == null) this.hidden else hidden.split(' ').toSeq.map(_.trim).filterNot(_.isEmpty),
    nonHidden = if (nonHidden == null) this.nonHidden else nonHidden.split(' ').toSeq.map(_.trim).filterNot(_.isEmpty),
    showing = if (showing == null) this.showing else showing.split(' ').toSeq.map(_.trim).filterNot(_.isEmpty),
    inTransition =
      if (inTransition == null) this.inTransition else inTransition.split(' ').toSeq.map(_.trim).filterNot(_.isEmpty),
    enter = if (enter == null) this.enter else enter.split(' ').toSeq.map(_.trim).filterNot(_.isEmpty),
    enterDuration =
      if (enterDuration == null) this.enterDuration
      else enterDuration.split(' ').toSeq.map(_.trim).filterNot(_.isEmpty),
    enterTiming =
      if (enterTiming == null) this.enterTiming else enterTiming.split(' ').toSeq.map(_.trim).filterNot(_.isEmpty),
    enterFrom = if (enterFrom == null) this.enterFrom else enterFrom.split(' ').toSeq.map(_.trim).filterNot(_.isEmpty),
    enterTo = if (enterTo == null) this.enterTo else enterTo.split(' ').toSeq.map(_.trim).filterNot(_.isEmpty),
    leave = if (leave == null) this.leave else leave.split(' ').toSeq.map(_.trim).filterNot(_.isEmpty),
    leaveDuration =
      if (leaveDuration == null) this.leaveDuration
      else leaveDuration.split(' ').toSeq.map(_.trim).filterNot(_.isEmpty),
    leaveTiming =
      if (leaveTiming == null) this.leaveTiming else leaveTiming.split(' ').toSeq.map(_.trim).filterNot(_.isEmpty),
    leaveFrom = if (leaveFrom == null) this.leaveFrom else leaveFrom.split(' ').toSeq.map(_.trim).filterNot(_.isEmpty),
    leaveTo = if (leaveTo == null) this.leaveTo else leaveTo.split(' ').toSeq.map(_.trim).filterNot(_.isEmpty),
    onEnterFrom = onEnterFrom,
    onEnterTo = onEnterTo,
    onLeaveFrom = onLeaveFrom,
    onLeaveTo = onLeaveTo,
    onReset = onReset
  )

}

object TransitionConfig {

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
