package app.tulz.laminext.site

import app.tulz.laminext.site.examples.TestExample
import app.tulz.laminext.site.pages.CodeExamplePage
import app.tulz.laminext.site.pages.IndexPage
import app.tulz.laminext.site.pages.PageRender

final case class Page(
  path: String,
  title: String,
  render: PageRender
)
