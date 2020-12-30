package app.tulz.laminext.site.examples

import com.raquo.laminar.api.L._

abstract class CodeExample[E <: Element](val id: String, val title: String, val description: String)(_code: sourcecode.Text[Element]) {

  val code: sourcecode.Text[Element] = _code.asInstanceOf[sourcecode.Text[Element]]

}
