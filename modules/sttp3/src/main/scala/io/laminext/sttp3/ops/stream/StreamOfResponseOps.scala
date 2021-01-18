package io.laminext.sttp3.ops.stream

import com.raquo.airstream.core.EventStream
import sttp.client3.Response

class StreamOfResponseOps[E, R](underlying: EventStream[Response[Either[E, R]]]) {

  def isSuccess: EventStream[Boolean] = underlying.map(_.code.isSuccess)

  def bodies: EventStream[Either[E, R]] =
    underlying.collect { case Response(body, _, _, _, _, _) => body }

  def okays: EventStream[R] =
    underlying.collect { case Response(Right(r), _, _, _, _, _) => r }

  def errors: EventStream[E] =
    underlying.collect { case Response(Left(e), _, _, _, _, _) => e }

}
