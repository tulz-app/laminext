package io.laminext.fetch

import com.raquo.laminar.api.L._
import io.laminext.fetch.ops.EventStreamOfFetchResponseOfEitherOps
import io.laminext.fetch.ops.EventStreamOfFetchResponseOps
import io.laminext.fetch.ops.FetchEventStreamBuilderOps
import io.laminext.util.UrlString

trait FetchSyntax {

  def url(
    absolute: String,
    params: Map[String, Seq[String]] = Map.empty
  ): RequestUrl =
    RequestUrl.fromLocation(UrlString.parse(absolute)).addParams(params)

  implicit def syntaxFetchEventStreamBuilder(
    underlying: FetchEventStreamBuilder
  ): FetchEventStreamBuilderOps = new FetchEventStreamBuilderOps(underlying)

  implicit def syntaxEventStreamOfFetchResponseOfEither[L, R](
    underlying: EventStream[FetchResponse[Either[L, R]]]
  ): EventStreamOfFetchResponseOfEitherOps[L, R] = new EventStreamOfFetchResponseOfEitherOps[L, R](underlying)

  implicit def syntaxEventStreamOfFetchResponse[A](
    underlying: EventStream[FetchResponse[A]]
  ): EventStreamOfFetchResponseOps[A] = new EventStreamOfFetchResponseOps[A](underlying)
}
