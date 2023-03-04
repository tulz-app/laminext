package io.laminext.site

import com.raquo.laminar.api.L._
//import factorio._
import frontroute.LocationProvider
import org.scalajs.dom

class Wiring(
  val ssrContext: SsrContext,
  val routes: Routes
)

object Wiring {

//  @blueprint
  class MainBlueprint {

//    @provides
    val ssrContext: SsrContext = SsrContext(
      ssr = dom.window.navigator.userAgent == "laminext/ssr"
    )

  }

  def apply(): Wiring = {
//    val assembler = Assembler[Wiring](new MainBlueprint)
//    assembler()
    new Wiring(
      ssrContext = SsrContext(
        ssr = dom.window.navigator.userAgent == "laminext/ssr"
      ),
      routes = new Routes
    )
  }

}
