package app.tulz.cookies

import scala.scalajs.js

@js.native
trait CookieAttributes extends js.Object {
  val domain: String = js.native
  val path: String   = js.native
}

object CookieAttributes {
  def apply(domain: String, path: String = "/"): CookieAttributes = {
    js.Dynamic
      .literal(
        path = path,
        domain = domain
      )
      .asInstanceOf[CookieAttributes]
  }
}
