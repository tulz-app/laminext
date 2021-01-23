package io.laminext

trait HtmlEntities {

  /**
   * Ellipsis
   */
  @inline def hellip: String = "…"

  /**
   * Vertical Ellipsis
   */
  @inline def vellip: String = "⋮"

  /**
   * En dash
   */
  @inline def ndash: String = "\u2013"

  /**
   * Em dash
   */
  @inline def mdash: String = "\u2014"

  import com.raquo.laminar.api.L._

  /**
   * HTML Entities
   */
  object HE {

    /**
     * Bullet
     */
    @inline def bull: String = "•"

    /**
     * Opening curly quotes
     */
    @inline def ldquo: String = "“"

    /**
     * Closing curly quotes
     */
    @inline def rdquo: String = "”"

    /**
     * Opening single curly quotes
     */
    @inline def lsquo: String = "‘"

    /**
     * Closing single curly quotes
     */
    @inline def rsquo: String = "’"

    /**
     * Left arrow
     */
    @inline def larr: String = "←"

    /**
     * Right arrow
     */
    @inline def rarr: String = "→"

    /**
     * Down arrow
     */
    @inline def darr: String = "↓"

    /**
     * Up arrow
     */
    @inline def uarr: String = "↑"

    /**
     * Copyright
     */
    @inline def copy: String = "©"

    /**
     * Trademark
     */
    @inline def trade: String = "™"

    /**
     * Registered
     */
    @inline def reg: String = "®"

  }
}
