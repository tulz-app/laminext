package io.laminext.fetch

package object circe extends FetchSyntax with FetchCirceSyntax {

  val Fetch: io.laminext.fetch.Fetch.type = io.laminext.fetch.Fetch
  type FetchEventStreamBuilder = io.laminext.fetch.FetchEventStreamBuilder
  type FetchException          = io.laminext.fetch.FetchException
  type FetchResponse[A]        = io.laminext.fetch.FetchResponse[A]
  type FetchTimeout            = io.laminext.fetch.FetchTimeout
  type FetchError              = io.laminext.fetch.FetchError
  type RequestUrl              = io.laminext.fetch.RequestUrl
  type ToRequestBody           = io.laminext.fetch.ToRequestBody
  val ToRequestBody: io.laminext.fetch.ToRequestBody.type = io.laminext.fetch.ToRequestBody
  type ToRequestUrl = io.laminext.fetch.ToRequestUrl
  val ToRequestUrl: io.laminext.fetch.ToRequestUrl.type = io.laminext.fetch.ToRequestUrl

}
