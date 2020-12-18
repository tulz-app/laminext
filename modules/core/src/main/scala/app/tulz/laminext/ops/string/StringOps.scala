package app.tulz.laminext.ops.string

import com.raquo.laminar.api.L._
import com.raquo.domtypes.generic.Modifier

class StringOps(s: String) {

  @inline def cls: Modifier[HtmlElement] = com.raquo.laminar.api.L.cls := s

}
