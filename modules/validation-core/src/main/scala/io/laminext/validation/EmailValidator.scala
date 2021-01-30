package io.laminext.validation

object EmailValidator {

  private val EmailRegex =
    """(^[a-zA-Z0-9\.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$)""".r

  def isValidEmail(email: String): Boolean = email match {
    case null                => false
    case s if s.trim.isEmpty => false
    case EmailRegex(_)       => true
    case _                   => false
  }

}
