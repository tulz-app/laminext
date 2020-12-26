package app.tulz.ui.transition

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
  leaveTo: Seq[String]
)

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
    leaveTo: String
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
    leaveTo = leaveTo.split(' ').toSeq.map(_.trim).filterNot(_.isEmpty)
  )

}
