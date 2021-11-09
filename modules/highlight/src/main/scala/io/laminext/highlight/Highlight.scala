package io.laminext.highlight

import scala.scalajs.js
import org.scalajs.dom.Node
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("highlight.js/lib/core", JSImport.Default)
object Highlight extends js.Any {

  def registerLanguage(name: String, lang: js.Any): Unit = js.native
  def highlightElement(block: Node): Unit                = js.native

}

@js.native
@JSImport("highlight.js/lib/languages/asciidoc", JSImport.Default)
object HighlightAsciiDoc extends js.Any

@js.native
@JSImport("highlight.js/lib/languages/bash", JSImport.Default)
object HighlightBash extends js.Any

@js.native
@JSImport("highlight.js/lib/languages/c", JSImport.Default)
object HighlightC extends js.Any

@js.native
@JSImport("highlight.js/lib/languages/c-like", JSImport.Default)
object HighlightClike extends js.Any

@js.native
@JSImport("highlight.js/lib/languages/clojure", JSImport.Default)
object HighlightClojure extends js.Any

@js.native
@JSImport("highlight.js/lib/languages/cpp", JSImport.Default)
object HighlightCpp extends js.Any

@js.native
@JSImport("highlight.js/lib/languages/css", JSImport.Default)
object HighlightCss extends js.Any

@js.native
@JSImport("highlight.js/lib/languages/diff", JSImport.Default)
object HighlightDiff extends js.Any

@js.native
@JSImport("highlight.js/lib/languages/elixir", JSImport.Default)
object HighlightElixir extends js.Any

@js.native
@JSImport("highlight.js/lib/languages/erlang", JSImport.Default)
object HighlightErlang extends js.Any

@js.native
@JSImport("highlight.js/lib/languages/fsharp", JSImport.Default)
object HighlightFsharp extends js.Any

@js.native
@JSImport("highlight.js/lib/languages/go", JSImport.Default)
object HighlightGo extends js.Any

@js.native
@JSImport("highlight.js/lib/languages/haskell", JSImport.Default)
object HighlightHaskell extends js.Any

@js.native
@JSImport("highlight.js/lib/languages/java", JSImport.Default)
object HighlightJava extends js.Any

@js.native
@JSImport("highlight.js/lib/languages/javascript", JSImport.Default)
object HighlightJavaScript extends js.Any

@js.native
@JSImport("highlight.js/lib/languages/json", JSImport.Default)
object HighlightJson extends js.Any

@js.native
@JSImport("highlight.js/lib/languages/less", JSImport.Default)
object HighlightLess extends js.Any

@js.native
@JSImport("highlight.js/lib/languages/lisp", JSImport.Default)
object HighlightLisp extends js.Any

@js.native
@JSImport("highlight.js/lib/languages/lua", JSImport.Default)
object HighlightLua extends js.Any

@js.native
@JSImport("highlight.js/lib/languages/markdown", JSImport.Default)
object HighlightMarkdown extends js.Any

@js.native
@JSImport("highlight.js/lib/languages/nginx", JSImport.Default)
object HighlightNginx extends js.Any

@js.native
@JSImport("highlight.js/lib/languages/objectivec", JSImport.Default)
object HighlightObjectiveC extends js.Any

@js.native
@JSImport("highlight.js/lib/languages/ocaml", JSImport.Default)
object HighlightOcaml extends js.Any

@js.native
@JSImport("highlight.js/lib/languages/pgsql", JSImport.Default)
object HighlightPgSql extends js.Any

@js.native
@JSImport("highlight.js/lib/languages/python", JSImport.Default)
object HighlightPython extends js.Any

@js.native
@JSImport("highlight.js/lib/languages/r", JSImport.Default)
object HighlightR extends js.Any

@js.native
@JSImport("highlight.js/lib/languages/ruby", JSImport.Default)
object HighlightRuby extends js.Any

@js.native
@JSImport("highlight.js/lib/languages/rust", JSImport.Default)
object HighlightRust extends js.Any

@js.native
@JSImport("highlight.js/lib/languages/scala", JSImport.Default)
object HighlightScala extends js.Any

@js.native
@JSImport("highlight.js/lib/languages/scss", JSImport.Default)
object HighlightScss extends js.Any

@js.native
@JSImport("highlight.js/lib/languages/sql", JSImport.Default)
object HighlightSql extends js.Any

@js.native
@JSImport("highlight.js/lib/languages/sql_more", JSImport.Default)
object HighlightSqlMore extends js.Any

@js.native
@JSImport("highlight.js/lib/languages/swift", JSImport.Default)
object HighlightSwift extends js.Any

@js.native
@JSImport("highlight.js/lib/languages/typescript", JSImport.Default)
object HighlightTypescript extends js.Any

@js.native
@JSImport("highlight.js/lib/languages/xml", JSImport.Default)
object HighlightXml extends js.Any

@js.native
@JSImport("highlight.js/lib/languages/yaml", JSImport.Default)
object HighlightYaml extends js.Any
