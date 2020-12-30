package app.tulz.laminext.site.icons

import com.raquo.laminar.api.L.svg._
import app.tulz.laminar.ext._
import app.tulz.laminext._
import org.scalajs.dom.svg.SVG

object Icons {

  private def faIcon(dString: String, vBox: String): AmendedSvgTag[SVG] =
    svg.amend(
      cls := "fill-current",
      viewBox := vBox,
      path(d := dString)
    )

  def chevronDown: AmendedSvgTag[SVG] =
    faIcon(
      "M4 181C7 178 12 176 16 176C20 176 24 177 27 180L224 362L421 180C428 174 438 175 444 181C450 188 449 198 443 204L235 396C229 401 219 401 213 396L5 204C-1 198 -2 188 4 181Z",
      "0 0 448 512"
    )

  def chevronUp: AmendedSvgTag[SVG] =
    faIcon(
      "M444 331C441 334 436 336 432 336C428 336 424 335 421 332L224 150L27 332C20 338 10 337 4 331C-2 324 -1 314 5 308L213 116C219 111 229 111 235 116L443 308C449 314 450 324 444 331Z",
      "0 0 448 512"
    )

}
