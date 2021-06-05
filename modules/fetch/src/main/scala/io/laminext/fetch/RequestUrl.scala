package io.laminext.fetch

import io.laminext.util.UrlUtils
import org.scalajs.dom.raw.Location

import scala.collection.immutable.ArraySeq
import scala.scalajs.js.URIUtils.encodeURIComponent

/**
 * @param protocol http or https
 * @param host hostname, optionally with port
 * @param path a Seq of path segments
 * @param params a Map of query search parameters
 */
case class RequestUrl private (
  protocol: String,
  host: String,
  path: Seq[String] = Seq.empty,
  params: Map[String, Seq[String]] = Map.empty
) extends ToRequestUrl {

  def encode: String = s"$protocol//$host/${path.map(encodeURIComponent).mkString("/")}${UrlUtils.encodeSearchParams(params)}"

  override def apply(): String = encode

  @inline def withHost(host: String): RequestUrl = this.copy(host = host)

  @inline def withSegments(segments: String*): RequestUrl =
    this.copy(
      path = segments.filterNot(_.isEmpty)
    )

  @inline def withPath(path: String): RequestUrl =
    this.withSegments(ArraySeq.unsafeWrapArray(path.split('/')): _*)

  @inline def addSegments(segments: String*): RequestUrl =
    this.withSegments(
      this.path ++ segments: _*
    )

  @inline def appendPath(path: String): RequestUrl =
    this.addSegments(ArraySeq.unsafeWrapArray(path.split('/')): _*)

  @inline def withParams(params: Map[String, Seq[String]]): RequestUrl =
    this.copy(params = params)

  @inline def withParams(params: (String, String)*): RequestUrl =
    this.withParams(params.groupMap(_._1)(_._2))

  @inline def addParams(params: Map[String, Seq[String]]): RequestUrl =
    this.withParams(this.params ++ params)

  @inline def addParams(params: (String, String)*): RequestUrl =
    this.addParams(params.groupMap(_._1)(_._2))

  def maybeAddParams(params: (String, Option[String])*): RequestUrl =
    this.addParams(params.collect { case (name, Some(value)) => (name, value) }: _*)

}

object RequestUrl {

  def apply(
    protocol: String = "https",
    host: String,
    path: Seq[String] = Seq.empty,
    params: Map[String, Seq[String]] = Map.empty
  ): RequestUrl =
    new RequestUrl(
      protocol = protocol,
      host = host,
      path = path.filterNot(_.isEmpty),
      params = params
    )

  def fromLocation(location: Location): RequestUrl = {
    val segments = if (location.pathname.nonEmpty) {
      location.pathname.split('/').toSeq
    } else {
      Seq.empty
    }
    RequestUrl(
      protocol = location.protocol,
      host = location.host,
      path = segments,
      params = UrlUtils.decodeSearchParams(location.search)
    )
  }

}
