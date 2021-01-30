package io.laminext.fetch

import scala.scalajs.js.URIUtils.encodeURIComponent

case class RequestUrl(
  protocol: String = "https",
  hostname: String,
  path: Seq[String] = Seq.empty,
  params: Map[String, Seq[String]] = Map.empty
) extends ToRequestUrl {

  private def formatParams: String =
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

  override def apply(): String = s"$protocol://$hostname/${path.map(encodeURIComponent).mkString("/")}$formatParams"

  @inline def withHostname(hostname: String): RequestUrl = this.copy(hostname = hostname)

  @inline def withPath(path: String): RequestUrl = this.copy(
    path = path.split('/').toSeq
  )

  @inline def addPath(path: String*): RequestUrl = this.copy(
    path = this.path ++ path
  )

  def withParams(params: (String, String)*): RequestUrl = this.copy(
    params = params.groupMap(_._1)(_._2)
  )

  def addParams(params: (String, String)*): RequestUrl =
    this.copy(params = params.groupMap(_._1)(_._2))

  def maybeAddParams(params: (String, Option[String])*): RequestUrl =
    this.addParams(params.collect { case (name, Some(value)) =>
      (name, value)
    }: _*)

}
