package io.laminext.markdown.markedjs

import scala.scalajs.js
import scala.scalajs.js.Any
import scala.scalajs.js.UndefOr

class MarkedOptions extends js.Object {

  /**
   * A function to highlight code blocks
   * @see
   *   [[https://github.com/chjj/marked#highlight]]
   */
  val highlight: UndefOr[(String, UndefOr[String], js.Function) => String] = js.undefined

  /**
   * An object containing functions to render tokens to HTML.
   */
  val renderer: MarkedRenderer = new MarkedRenderer()

  /**
   * Enable [[https://help.github.com/articles/github-flavored-markdown GitHub flavored markdown]].
   */
  val gfm: Boolean = true

  /**
   * Enable GFM [[https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet#wiki-tables tables]]. This option requires the gfm option to be true.
   */
  val tables: Boolean = true

  /**
   * Enable GFM [[https://help.github.com/articles/github-flavored-markdown#newlines line breaks]]. This option requires the gfm option to be true.
   */
  val breaks: Boolean = false

  /**
   * Conform to obscure parts of `markdown.pl` as much as possible. Don't fix any of the original markdown bugs or poor behavior.
   */
  val pedantic: Boolean = false

  /**
   * Sanitize the output. Ignore any HTML that has been input.
   */
  val sanitize: Boolean = false

  /**
   * Use smarter list behavior than the original markdown. May eventually be default with the old behavior moved into `pedantic`.
   */
  val smartLists: Boolean = true

  /**
   * Use "smart" typograhic punctuation for things like quotes and dashes.
   */
  val smartypants: Boolean = false
}

object MarkedOptions {

  /**
   * @param highlight
   *   A function to highlight code blocks
   * @param renderer
   *   An object containing functions to render tokens to HTML.
   * @param gfm
   *   Enable [[https://help.github.com/articles/github-flavored-markdown GitHub flavored markdown]].
   * @param tables
   *   Enable GFM [[https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet#wiki-tables tables]]. This option requires the gfm option to be true.
   * @param breaks
   *   Enable GFM [[https://help.github.com/articles/github-flavored-markdown#newlines line breaks]]. This option requires the gfm option to be true.
   * @param pedantic
   *   Conform to obscure parts of `markdown.pl` as much as possible. Don't fix any of the original markdown bugs or poor behavior.
   * @param sanitize
   *   Sanitize the output. Ignore any HTML that has been input.
   * @param smartLists
   *   Use smarter list behavior than the original markdown. May eventually be default with the old behavior moved into `pedantic`.
   * @param smartypants
   *   Use "smart" typograhic punctuation for things like quotes and dashes.
   */
  def apply(highlight: UndefOr[(String, UndefOr[String], js.Function) => String] = js.undefined,
            renderer: MarkedRenderer = new MarkedRenderer(),
            gfm: Boolean = true,
            tables: Boolean = true,
            breaks: Boolean = false,
            pedantic: Boolean = false,
            sanitize: Boolean = false,
            smartLists: Boolean = true,
            smartypants: Boolean = false
  ): MarkedOptions = {
    val _highlight   = highlight
    val _renderer    = renderer
    val _gfm         = gfm
    val _tables      = tables
    val _breaks      = breaks
    val _pedantic    = pedantic
    val _sanitize    = sanitize
    val _smartLists  = smartLists
    val _smartypants = smartypants
    new MarkedOptions {
      override val highlight: UndefOr[(String, UndefOr[String], js.Function) => String] = _highlight
      override val renderer: MarkedRenderer                                             = _renderer
      override val gfm: Boolean                                                         = _gfm
      override val tables: Boolean                                                      = _tables
      override val breaks: Boolean                                                      = _breaks
      override val pedantic: Boolean                                                    = _pedantic
      override val sanitize: Boolean                                                    = _sanitize
      override val smartLists: Boolean                                                  = _smartLists
      override val smartypants: Boolean                                                 = _smartypants
    }
  }
}
