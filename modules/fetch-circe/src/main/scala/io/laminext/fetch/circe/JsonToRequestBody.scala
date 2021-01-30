package io.laminext.fetch.circe

import io.laminext.fetch.ToRequestBody
import org.scalajs.dom.experimental.BodyInit

import scala.scalajs.js
import scala.scalajs.js.UndefOr

class JsonToRequestBody(jsonStr: String) extends ToRequestBody {

  override def apply(): UndefOr[BodyInit] = jsonStr

  override def updateHeaders(headers: js.UndefOr[Map[String, String]]): js.UndefOr[Map[String, String]] =
    headers.getOrElse(Map.empty).updated("content-type", "application/json; charset=utf-8")

}
