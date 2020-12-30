package io.laminext.syntax

import com.raquo.airstream.eventstream.EventStream
import io.laminext.ops.stream.EventStreamOfOptionOps

trait EventStreamOfOptionSyntax {

  implicit def syntaxEventStreamOfOption[A](
    s: EventStream[Option[A]]
  ): EventStreamOfOptionOps[A] = new EventStreamOfOptionOps[A](s)

}
