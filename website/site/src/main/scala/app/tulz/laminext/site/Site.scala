package app.tulz.laminext.site

import app.tulz.laminext.site.examples.CodeExample
import app.tulz.laminext.site.examples.example1.TestExample
import app.tulz.laminext.site.pages.CodeExamplePage
import app.tulz.laminext.site.pages.DocumentationPage
import app.tulz.laminext.site.pages.IndexPage
import app.tulz.website.macros.FileToLiteral
import app.tulz.laminext.site.examples

object Site {

  private def examplePage(
    example: CodeExample
  ): Page = Page(example.id, example.title, CodeExamplePage(example))

  private def docPage(
    path: String,
    title: String,
    markdown: String
  ): Page = Page(path, title, DocumentationPage(title, markdown))

  val indexModule: SiteModule =
    SiteModule(
      path = "laminext",
      index = docPage("", "laminext", FileToLiteral("/doc/laminext/index.md")),
      navigation = Seq(
        "Documentation" -> Seq(
          docPage("doc-1", "Doc 1", FileToLiteral("/doc/test-doc.md"))
        ),
        "Examples" -> Seq(
          examplePage(TestExample)
        )
      )
    )

  val modules: Seq[SiteModule] = Seq(
    indexModule,
    SiteModule(
      path = "core",
      index = docPage("", "Core", FileToLiteral("/doc/core/index.md")),
      navigation = Seq(
        "Examples" -> Seq(
          examplePage(examples.signal.ex_transitions.SignalTransitionsExample)
        )
      )
    ),
    SiteModule(
      path = "ui",
      index = Page("", "UI", IndexPage.render)
    ),
    SiteModule(
      path = "tailwind",
      index = Page("", "Tailwind", IndexPage.render)
    ),
    SiteModule(
      path = "util",
      index = Page("", "Util", IndexPage.render)
    ),
    SiteModule(
      path = "fsm",
      index = Page("", "FSM", IndexPage.render)
    ),
    SiteModule(
      path = "markdown",
      index = Page("", "Markdown", IndexPage.render)
    ),
    SiteModule(
      path = "videojs",
      index = Page("", "video.js", IndexPage.render)
    ),
    SiteModule(
      path = "news",
      index = Page("", "News", IndexPage.render)
    ),
    SiteModule(
      path = "releases",
      index = Page("", "Releases", IndexPage.render)
    )
  )

  def findModule(path: String): Option[SiteModule] =
    modules.find(_.path == path)

}
