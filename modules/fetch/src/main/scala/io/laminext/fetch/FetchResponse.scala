package io.laminext.fetch

import org.scalajs.dom.ByteString
import org.scalajs.dom.Headers
import org.scalajs.dom.ResponseType

/**
 * @param data
 *   Contains the extracted response data (string, json, blob, etc)
 * @param `type`
 *   Contains the type of the response.
 * @param url
 *   Contains the URL of the response.
 * @param ok
 *   Contains a boolean stating whether the response was successful (status in the range 200-299) or not.
 * @param status
 *   Contains the status code of the response (e.g., 200 for a success).
 * @param statusText
 *   Contains the status message corresponding to the status code (e.g., OK for 200).
 * @param headers
 *   Contains the Headers object associated with the response.
 * @tparam A
 *   type of the extracted data (String, js.Any for json, dom.Blob, etc)
 */
final case class FetchResponse[A](
  ok: Boolean,
  status: Int,
  statusText: ByteString,
  headers: Headers,
  `type`: ResponseType,
  data: A,
  url: String,
) {

  def map[B](project: A => B): FetchResponse[B] = FetchResponse(
    ok = ok,
    status = status,
    statusText = statusText,
    headers = headers,
    `type` = `type`,
    data = project(data),
    url = url
  )

}
