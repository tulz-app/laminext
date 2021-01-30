package io.laminext.fetch

trait ToRequestUrl {

  def apply(): String

}

object ToRequestUrl {

  implicit def stringRequestUri(uri: String): ToRequestUrl = () => uri

}
