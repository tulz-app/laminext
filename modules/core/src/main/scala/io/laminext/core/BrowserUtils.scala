package io.laminext.core

import org.scalajs.dom
import scala.util.Try

object BrowserUtils {

  val storageEnabled: Boolean = Try(dom.window.localStorage).fold(
    { _ =>
      dom.console.debug("window.localStorage is not accessible.")
      false
    },
    _ => true
  )

}
