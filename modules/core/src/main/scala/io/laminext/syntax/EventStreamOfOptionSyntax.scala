package io.laminext.syntax

import com.raquo.laminar.api.L._
import io.laminext.core.ops.stream.EventStreamOfOptionOps

trait EventStreamOfOptionSyntax {

  implicit def syntaxEventStreamOfOption[A](
    s: EventStream[Option[A]]
  ): EventStreamOfOptionOps[A] = new EventStreamOfOptionOps[A](s)

}
