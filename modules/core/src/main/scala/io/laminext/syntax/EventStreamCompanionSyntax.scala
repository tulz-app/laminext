package io.laminext.syntax

import com.raquo.airstream.eventstream.EventStream
import io.laminext.ops.stream.EventStreamCompanionOps

trait EventStreamCompanionSyntax {

  implicit def syntaxEventStreamCompanion(
    e: EventStream.type
  ): EventStreamCompanionOps.type = EventStreamCompanionOps

}
