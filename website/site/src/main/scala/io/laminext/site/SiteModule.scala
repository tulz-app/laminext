package io.laminext.site

case class SiteModule(
  path: String,
  index: Page,
  navigation: Seq[(String, Seq[Page])] = Seq.empty
) {

  def findPage(path: String): Option[Page] = {
    navigation.flatMap(_._2).find(_.path == path)
  }

}
