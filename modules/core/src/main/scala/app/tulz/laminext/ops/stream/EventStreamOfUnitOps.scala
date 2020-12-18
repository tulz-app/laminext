package app.tulz.laminext.ops.stream

import com.raquo.airstream.core.Observable
import com.raquo.airstream.eventstream.EventStream
import com.raquo.airstream.features.FlattenStrategy

final class EventStreamOfUnitOps(s: EventStream[Unit]) {

  @inline def flatMapTo[B, Inner[_], Output[+_] <: Observable[_]](inner: => Inner[B])(implicit strategy: FlattenStrategy[EventStream, Inner, Output]): Output[B] = {
    s.flatMap(_ => inner)
  }

}
