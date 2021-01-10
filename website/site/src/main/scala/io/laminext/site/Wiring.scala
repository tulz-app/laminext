package io.laminext.site

import com.raquo.laminar.api.L._
import factorio._
import io.frontroute.BrowserNavigation
import io.frontroute.LocationProvider
import org.scalajs.dom

class Wiring(
  val ssrContext: SsrContext,
  val routes: Routes
)

object Wiring {

  @blueprint
  class MainBlueprint {

    @provides
    val provideLocationProvider: LocationProvider = LocationProvider.browser(windowEvents.onPopState)

    @provides
    val ssrContext: SsrContext = SsrContext(
      ssr = dom.window.navigator.userAgent == "laminext/ssr"
    )

  }

  def apply(): Wiring = {
    val assembler = Assembler[Wiring](new MainBlueprint)
    assembler()
  }

}
