package io.laminext.site

import com.raquo.laminar.api.L._
import io.frontroute._
import io.frontroute.DocumentMeta

package object pages {

  def page(
    title: String,
    description: Option[String] = None,
    keywords: Option[String] = None,
    status: PageStatusCode = PageStatusCode.Ok
  )(
    content: => Element
  ): Element =
    content.amend(
      onMountCallback { _ =>
        DocumentMeta.set(
          title = title,
          description = description,
          keywords = keywords,
          status = status
        )
      }
    )

}
