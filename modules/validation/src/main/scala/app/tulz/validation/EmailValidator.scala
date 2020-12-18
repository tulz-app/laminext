package app.tulz.validation

object EmailValidator {

  private val EmailRegex =
    """^[a-zA-Z0-9\.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$""".r

  def isValidEmail(e: String): Boolean = e match {
    case null                => false
    case s if s.trim.isEmpty => false
    case EmailRegex(email)   => true
  }

}
