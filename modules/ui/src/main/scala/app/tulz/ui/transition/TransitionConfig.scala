package app.tulz.ui.transition

case class TransitionConfig(
  hidden: String,
  enter: String,
  enterFrom: String,
  enterTo: String,
  leave: String,
  leaveFrom: String,
  leaveTo: String
) {

  val hiddenClasses: Seq[String]    = hidden.split("\\s+").toSeq
  val enterClasses: Seq[String]     = enter.split("\\s+").toSeq
  val enterFromClasses: Seq[String] = enterFrom.split("\\s+").toSeq
  val enterToClasses: Seq[String]   = enterTo.split("\\s+").toSeq
  val leaveClasses: Seq[String]     = leave.split("\\s+").toSeq
  val leaveFromClasses: Seq[String] = leaveFrom.split("\\s+").toSeq
  val leaveToClasses: Seq[String]   = leaveTo.split("\\s+").toSeq

}
