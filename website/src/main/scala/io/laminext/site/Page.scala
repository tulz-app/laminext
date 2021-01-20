package io.laminext.site

import io.laminext.site.pages.PageRender

final case class Page(
  path: String,
  title: String,
  render: PageRender
)
