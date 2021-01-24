package io.laminext.core
package ops.stream

import com.raquo.laminar.api.L._
import com.raquo.airstream.flatten.FlattenStrategy

final class EventStreamOfUnitOps(s: EventStream[Unit]) {

  @inline def flatMapTo[B, Inner[_], Output[+_] <: Observable[_]](
    inner: => Inner[B]
  )(implicit strategy: FlattenStrategy[EventStream, Inner, Output]): Output[B] = {
    s.flatMap(_ => inner)
  }

}
