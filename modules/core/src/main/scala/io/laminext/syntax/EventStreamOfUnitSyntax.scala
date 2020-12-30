package io.laminext.syntax

import com.raquo.airstream.eventstream.EventStream
import io.laminext.ops.stream.EventStreamOfUnitOps

trait EventStreamOfUnitSyntax {

  implicit def syntaxEventStreamOfUnit(
    s: EventStream[Unit]
  ): EventStreamOfUnitOps = new EventStreamOfUnitOps(s)

}
