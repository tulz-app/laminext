package io.laminext.site.icons

import com.raquo.laminar.api.L
import com.raquo.laminar.api.L.svg._
import io.laminext.syntax.all._
import io.laminext._
import org.scalajs.dom.svg.SVG

object Icons {

  private def faIcon(d: String, viewBox: String): AmendedSvgTag[SVG] =
    svg.amend(
      cls := "fill-current",
      L.svg.viewBox := viewBox,
      path(L.svg.d := d)
    )

  def chevronDown: AmendedSvgTag[SVG] =
    faIcon(
      d =
        "M4 181C7 178 12 176 16 176C20 176 24 177 27 180L224 362L421 180C428 174 438 175 444 181C450 188 449 198 443 204L235 396C229 401 219 401 213 396L5 204C-1 198 -2 188 4 181Z",
      viewBox = "0 0 448 512"
    )

  def chevronUp: AmendedSvgTag[SVG] =
    faIcon(
      d =
        "M444 331C441 334 436 336 432 336C428 336 424 335 421 332L224 150L27 332C20 338 10 337 4 331C-2 324 -1 314 5 308L213 116C219 111 229 111 235 116L443 308C449 314 450 324 444 331Z",
      viewBox = "0 0 448 512"
    )

  def check: AmendedSvgTag[SVG] =
    faIcon(
      d =
        "M475 123L203 395C200 398 196 400 192 400S184 398 181 395L37 251C30 245 30 235 37 229S53 222 59 229L192 361L453 101C459 94 469 94 475 101S482 117 475 123Z",
      viewBox = "0 0 512 512"
    )

}
