package io.laminext.site

import com.raquo.laminar.api.L._

package object pages {

  type PageRender = () => PageResult
  type PageResult = Either[(Int, String), (Element, String)]

  def page(title: String)(content: () => Element): PageRender = () => Right((content(), title))
  def error(code: Int, message: String): PageRender           = () => Left((code, message))

}
