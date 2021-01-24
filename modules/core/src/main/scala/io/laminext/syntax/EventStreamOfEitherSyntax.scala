package io.laminext.syntax

import com.raquo.airstream.core.EventStream
import io.laminext.core.ops.stream.EventStreamOfEitherOps

trait EventStreamOfEitherSyntax {

  implicit def syntaxEventStreamOfEither[A, B](
    s: EventStream[Either[A, B]]
  ): EventStreamOfEitherOps[A, B] = new EventStreamOfEitherOps[A, B](s)

}
