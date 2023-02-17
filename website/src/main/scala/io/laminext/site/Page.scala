package io.laminext.site

import com.raquo.laminar.api.L._

final class Page private (
  val path: String,
  val link: String,
  val title: String,
  val render: () => Element
)

object Page {

  def apply(
    path: String,
    link: String,
    title: String,
    render: => Element
  ): Page = new Page(
    path,
    link,
    title,
    () => render
  )

}
