package app.tulz.laminext.site

import app.tulz.laminext.site.examples.CodeExample
import app.tulz.laminext.site.examples.TestExample
import app.tulz.laminext.site.pages.CodeExamplePage
import app.tulz.laminext.site.pages.IndexPage

object Site {

  private def examplePage(
    path: String,
    example: CodeExample[_]
  ): Page = Page(path, example.title, CodeExamplePage(example))

  private val navigationPages = Seq(
    Page("/", "Home", IndexPage.render)
  )

  private val examplePages = Seq(
    examplePage("/example-1", TestExample),
    examplePage("/example-2", TestExample),
    examplePage("/example-3", TestExample),
    examplePage("/example-4", TestExample),
    examplePage("/example-5", TestExample)
  )

  private val documentationPages = Seq(
    examplePage("/doc-1", TestExample)
  )

  private val allPages = navigationPages ++ documentationPages ++ examplePages

  def findPage(path: Seq[String]): Option[Page] = {
    val concated = path.mkString("/", "/", "")
    allPages.find(_.path == concated)
  }

  val navigationSections: Seq[(String, Seq[Page])] = Seq(
    "Navigation"    -> navigationPages,
    "Documentation" -> documentationPages,
    "Examples"      -> examplePages
  )

}
