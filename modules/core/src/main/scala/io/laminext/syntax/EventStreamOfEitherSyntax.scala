package io.laminext.syntax

import com.raquo.airstream.eventstream.EventStream
import io.laminext.ops.stream.EventStreamOfEitherOps

trait EventStreamOfEitherSyntax {

  implicit def syntaxEventStreamOfEither[A, B](
    s: EventStream[Either[A, B]]
  ): EventStreamOfEitherOps[A, B] = new EventStreamOfEitherOps[A, B](s)

}
