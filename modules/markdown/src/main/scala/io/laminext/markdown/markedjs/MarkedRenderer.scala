package io.laminext.markdown.markedjs

import scala.scalajs.js
import scala.scalajs.js.UndefOr
import scala.scalajs.js.annotation.JSGlobal

//noinspection SpellCheckingInspection
@js.native
@JSGlobal("marked.Renderer")
class MarkedRenderer extends js.Object {
  // Block level renderer methods
  def code(code: String, language: String): String = js.native
  def blockquote(quote: String): String            = js.native
  def html(html: String): String                   = js.native
  def heading(text: String, level: Int): String    = js.native
  def hr(): String                                 = js.native
  def list(body: String, ordered: Boolean): String = js.native
  def listitem(text: String): String               = js.native
  def paragraph(text: String): String              = js.native
  def table(header: String, body: String): String  = js.native
  def tablerow(content: String): String            = js.native

  /**
   * @note
   *   `flags` has the following properties: { header: true || false, align: 'center' || 'left' || 'right' }
   */
  def tablecell(content: String, flags: js.Object): String = js.native

  // Inline level renderer methods
  def strong(text: String): String                             = js.native
  def em(text: String): String                                 = js.native
  def codespan(code: String): String                           = js.native
  def br(): String                                             = js.native
  def del(text: String): String                                = js.native
  def link(href: String, title: String, text: String): String  = js.native
  def image(href: String, title: String, text: String): String = js.native
}

object MarkedRenderer {
  def apply(code: UndefOr[(String, String) => String] = js.undefined,
            blockquote: UndefOr[String => String] = js.undefined,
            html: UndefOr[String => String] = js.undefined,
            heading: UndefOr[(String, Int) => String] = js.undefined,
            hr: UndefOr[() => String] = js.undefined,
            list: UndefOr[(String, Boolean) => String] = js.undefined,
            listitem: UndefOr[String => String] = js.undefined,
            paragraph: UndefOr[String => String] = js.undefined,
            table: UndefOr[(String, String) => String] = js.undefined,
            tablerow: UndefOr[String => String] = js.undefined,
            tablecell: UndefOr[(String, js.Object) => String] = js.undefined,
            strong: UndefOr[String => String] = js.undefined,
            em: UndefOr[String => String] = js.undefined,
            codespan: UndefOr[String => String] = js.undefined,
            br: UndefOr[() => String] = js.undefined,
            del: UndefOr[String => String] = js.undefined,
            link: UndefOr[(String, String, String) => String] = js.undefined,
            image: UndefOr[(String, String, String) => String] = js.undefined
  ): MarkedRenderer = {
    val renderer = new MarkedRenderer().asInstanceOf[js.Dynamic]
    code.foreach(renderer.code = _)
    blockquote.foreach(renderer.blockquote = _)
    html.foreach(renderer.html = _)
    heading.foreach(renderer.heading = _)
    hr.foreach(renderer.hr = _)
    list.foreach(renderer.list = _)
    listitem.foreach(renderer.listitem = _)
    paragraph.foreach(renderer.paragraph = _)
    table.foreach(renderer.table = _)
    tablerow.foreach(renderer.tablerow = _)
    tablecell.foreach(renderer.tablecell = _)
    strong.foreach(renderer.strong = _)
    em.foreach(renderer.em = _)
    codespan.foreach(renderer.codespan = _)
    br.foreach(renderer.br = _)
    del.foreach(renderer.del = _)
    link.foreach(renderer.link = _)
    image.foreach(renderer.image = _)
    renderer.asInstanceOf[MarkedRenderer]
  }
}
