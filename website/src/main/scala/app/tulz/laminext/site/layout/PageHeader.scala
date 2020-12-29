package app.tulz.laminext.site.layout

import com.raquo.laminar.api.L._
import app.tulz.laminext.site.Page
import com.raquo.airstream.signal.Signal
import com.raquo.laminar.nodes.ReactiveHtmlElement

object PageHeader {

  def apply(
    $page: Signal[Option[Page]]
  ): ReactiveHtmlElement.Base = div(
    cls := "bg-cool-gray-900 text-white p-4",
    "laminext"
  )

}
