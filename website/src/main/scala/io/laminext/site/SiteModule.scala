package io.laminext.site

class SiteModule private (
  val path: String,
  val title: String,
  val index: Page,
  val navigation: Seq[(String, Seq[Page])]
) {

  def findPage(path: String): Option[Page] = {
    navigation.flatMap(_._2).find(_.path == path)
  }

}

object SiteModule {

  def apply(
    path: String,
    title: String,
    index: Page,
    navigation: (String, Seq[Page])*
  ): SiteModule = new SiteModule(path, title, index, navigation)

}
