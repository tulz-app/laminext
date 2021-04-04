package io.laminext.fetch

trait ReExports {

  type FetchEventStreamBuilder = io.laminext.fetch.FetchEventStreamBuilder
  type FetchException          = io.laminext.fetch.FetchException
  type FetchResponse[A]        = io.laminext.fetch.FetchResponse[A]
  type FetchTimeout            = io.laminext.fetch.FetchTimeout
  type FetchError              = io.laminext.fetch.FetchError
  type RequestUrl              = io.laminext.fetch.RequestUrl
  type ToRequestBody           = io.laminext.fetch.ToRequestBody
  type ToRequestUrl            = io.laminext.fetch.ToRequestUrl

  val Fetch: io.laminext.fetch.Fetch.type                 = io.laminext.fetch.Fetch
  val ToRequestBody: io.laminext.fetch.ToRequestBody.type = io.laminext.fetch.ToRequestBody
  val ToRequestUrl: io.laminext.fetch.ToRequestUrl.type   = io.laminext.fetch.ToRequestUrl

}
