package io.laminext.fetch

import com.raquo.laminar.api.L._
import io.laminext.fetch.ops.EventStreamOfFetchResponseOfEitherOps
import io.laminext.fetch.ops.EventStreamOfFetchResponseOps
import io.laminext.fetch.ops.FetchEventStreamBuilderOps

trait FetchSyntax {

  def uri(
    origin: String,
    path: String = "",
    params: Map[String, Seq[String]] = Map.empty
  ): RequestUrl = origin.split("://", 2) match {
    case Array(protocol, hostname) =>
      RequestUrl(protocol, hostname, path.split('/').toSeq, params)
    case _ =>
      RequestUrl("https", origin, path.split('/').toSeq, params)
  }

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
