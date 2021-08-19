package io.laminext.markdown.markedjs

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/**
 * @see
 *   [[https://github.com/chjj/marked#usage]]
 */
@js.native
@JSImport("marked", JSImport.Namespace)
object Marked extends js.Object {

  /**
   * @param markdownString
   *   String of markdown source to be compiled.
   * @param options
   *   Hash of options. Can also be set using the `marked.setOptions` method as seen above.
   * @param callback
   *   Function called when the `markdownString` has been fully parsed when using async highlighting. If the options argument is omitted, this can be used as
   *   the second argument.
   */
  def parse(markdownString: String, options: js.Object = ???, callback: js.Function = ???): String = js.native

  def setOptions(options: js.Object): Unit = js.native

}
