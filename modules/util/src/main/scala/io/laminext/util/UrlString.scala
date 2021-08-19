package io.laminext.util

import org.scalajs.dom
import org.scalajs.dom.raw.HTMLAnchorElement
import org.scalajs.dom.raw.Location
import scala.scalajs.js

object UrlString {

  def parse(url: String): Location = {
    val l = dom.document.createElement("a").asInstanceOf[HTMLAnchorElement]
    l.href = url
    js.Dynamic
      .literal(
        hash = l.hash,
        protocol = l.protocol,
        search = l.search,
        href = l.href,
        hostname = l.hostname,
        port = l.port,
        pathname = l.pathname,
        host = l.host
      )
      .asInstanceOf[Location]
  }

}
