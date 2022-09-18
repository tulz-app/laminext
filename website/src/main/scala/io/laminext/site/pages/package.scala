package io.laminext.site

import com.raquo.laminar.api.L._
import io.frontroute._
import io.frontroute.internal.Document

package object pages {

  type PageRender = () => PageResult
  type PageResult = Either[(Int, String), (Element, String)]

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
        Document.updateMeta(
          DocumentMeta(
            title = title,
            description = description,
            keywords = keywords,
            status = status
          )
        )
      }
    )

}
