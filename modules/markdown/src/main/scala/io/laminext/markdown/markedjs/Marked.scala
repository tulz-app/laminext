package io.laminext.markdown.markedjs

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/**
 * @see [[https://github.com/chjj/marked#usage]]
 */
@js.native
@JSImport("marked", JSImport.Default)
object Marked extends js.Function3[String, js.Object, js.Function, String] {

  def apply(markdownString: String, options: js.Object = ???, callback: js.Function = ???): String = js.native

  def setOptions(options: js.Object): Unit = js.native

}
