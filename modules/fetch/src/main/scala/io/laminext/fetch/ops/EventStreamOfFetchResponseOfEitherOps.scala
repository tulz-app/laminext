package io.laminext.fetch.ops

import com.raquo.laminar.api.L._
import io.laminext.fetch.FetchResponse

class EventStreamOfFetchResponseOfEitherOps[L, R](underlying: EventStream[FetchResponse[Either[L, R]]]) {

  def collectRight: EventStream[FetchResponse[R]] =
    underlying.collect { case FetchResponse(ok, status, statusText, headers, tpe, Right(data), url) =>
      FetchResponse(ok, status, statusText, headers, tpe, data, url)
    }

  def collectLeft: EventStream[FetchResponse[L]] =
    underlying.collect { case FetchResponse(ok, status, statusText, headers, tpe, Left(data), url) =>
      FetchResponse(ok, status, statusText, headers, tpe, data, url)
    }

  def rightData: EventStream[R] =
    underlying.collect { case FetchResponse(_, _, _, _, _, Right(data), _) => data }

  def leftData: EventStream[L] =
    underlying.collect { case FetchResponse(_, _, _, _, _, Left(data), _) => data }

}
