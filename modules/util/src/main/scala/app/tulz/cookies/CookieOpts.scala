package app.tulz.cookies

import scala.scalajs.js

@js.native
trait CookieOpts extends js.Object {
  val expires: Int    = js.native
  val path: String    = js.native
  val domain: String  = js.native
  val secure: Boolean = js.native
}

object CookieOpts {
  def apply(
    expires: Int,
    path: String = "/",
    domain: String = null,
    secure: Boolean = false
  ): CookieOpts = {
    js.Dynamic
      .literal(
        expires = expires,
        path = path,
        domain = domain,
        secure = secure
      )
      .asInstanceOf[CookieOpts]
  }
}
