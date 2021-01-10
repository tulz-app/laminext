package io.laminext

import io.circe._
import org.scalajs.dom.experimental.BodyInit

import scala.scalajs.js
import scala.scalajs.js.UndefOr

package object fetch {

  implicit def jsonRequestBody[A](
    value: A
  )(implicit encoder: Encoder[A]): RequestBody = new RequestBody {

    override def apply(): UndefOr[BodyInit] = encoder(value).noSpaces

    override def updateHeaders(headers: js.UndefOr[Map[String, String]]): js.UndefOr[Map[String, String]] =
      headers.getOrElse(Map.empty).updated("content-type", "application/json")

  }

  implicit def fetchEventStreamBuilderSyntax(b: FetchEventStreamBuilder): FetchEventStreamBuilderOps = new FetchEventStreamBuilderOps(b)

}
