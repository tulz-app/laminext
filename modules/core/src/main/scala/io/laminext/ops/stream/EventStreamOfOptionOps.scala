package io.laminext.ops.stream

import com.raquo.airstream.eventstream.EventStream

final class EventStreamOfOptionOps[A](s: EventStream[Option[A]]) {

  def collectDefined: EventStream[A] =
    s.collect { case Some(event) => event }

}
