package io.laminext.fetch

import org.scalajs.dom
import org.scalajs.dom.experimental.BodyInit

import scala.scalajs.js

trait RequestBody {

  def apply(): js.UndefOr[BodyInit]
  def updateHeaders(headers: js.UndefOr[Map[String, String]]): js.UndefOr[Map[String, String]] = headers

}

object RequestBody {

  val noBody: RequestBody = () => js.undefined

  implicit def blobRequestBody(blob: dom.Blob): RequestBody = () => blob

  implicit def bufferSourceRequestBody(bufferSource: dom.crypto.BufferSource): RequestBody = () => bufferSource

  implicit def formDataRequestBody(formData: dom.FormData): RequestBody = () => formData

  implicit def stringRequestBody(string: String): RequestBody = () => string

}
