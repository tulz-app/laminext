package io.laminext.syntax

import com.raquo.airstream.core.EventStream
import io.laminext.ops.stream.EventStreamOfUnitOps

trait EventStreamOfUnitSyntax {

  implicit def syntaxEventStreamOfUnit(
    s: EventStream[Unit]
  ): EventStreamOfUnitOps = new EventStreamOfUnitOps(s)

}
