package io.laminext.util

import scala.scalajs.js.URIUtils.encodeURIComponent

object UrlUtils {

  def encodeSearchParams(params: Map[String, Seq[String]]): String =
    if (params.isEmpty) {
      ""
    } else {
      s"?${params
        .flatMap { case (name, values) =>
          values.map { value =>
            s"${encodeURIComponent(name)}=${encodeURIComponent(value)}"
          }
        }.mkString("&")}"
    }

  def decodeSearchParams(search: String): Map[String, Seq[String]] =
    if (search.isEmpty || !search.startsWith("?")) {
      Map.empty
    } else {
      search
        .drop(1)
        .split('&')
        .toSeq
        .map(_.split("=", 2))
        .collect {
          case Array(key, value) => (key, value)
          case Array(key)        => (key, "")
        }
        .groupMap(_._1)(_._2)
    }

}
