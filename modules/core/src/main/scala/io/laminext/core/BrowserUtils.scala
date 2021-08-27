package io.laminext.core

import org.scalajs.dom.ext.LocalStorage
import org.scalajs.dom
import scala.util.Try

object BrowserUtils {

  val storageEnabled: Boolean = Try(LocalStorage).fold({ _ =>
                                                         dom.console.debug("Local storage is not accessible.")
                                                         false
                                                       },
                                                       _ => true
  )

}
