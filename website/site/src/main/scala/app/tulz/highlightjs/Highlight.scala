package app.tulz.highlightjs

import scala.scalajs.js
import org.scalajs.dom.raw.Node
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("highlight.js/lib/core", JSImport.Default)
object Highlight extends js.Any {

  def registerLanguage(name: String, lang: js.Any): Unit = js.native
  def highlightBlock(block: Node): Unit                  = js.native

}

@js.native
@JSImport("highlight.js/lib/languages/scala", JSImport.Default)
object HighlightScala extends js.Any
