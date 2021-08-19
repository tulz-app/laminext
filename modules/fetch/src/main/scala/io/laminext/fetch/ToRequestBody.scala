package io.laminext.fetch

import org.scalajs.dom
import org.scalajs.dom.experimental.BodyInit
import scala.scalajs.js

trait ToRequestBody {

  def apply(): js.UndefOr[BodyInit]
  def updateHeaders(headers: js.UndefOr[Map[String, String]]): js.UndefOr[Map[String, String]] = headers

}

object ToRequestBody {

  val noBody: ToRequestBody = () => js.undefined

  implicit def blobRequestBody(blob: dom.Blob): ToRequestBody = () => blob

  implicit def bufferSourceRequestBody(bufferSource: dom.crypto.BufferSource): ToRequestBody = () => bufferSource

  implicit def formDataRequestBody(formData: dom.FormData): ToRequestBody = () => formData

  implicit def stringRequestBody(string: String): ToRequestBody = () => string

}
