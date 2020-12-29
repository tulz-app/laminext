package app.tulz.laminext.ops.stream

import com.raquo.airstream.eventstream.EventStream
import com.raquo.airstream.eventstream.EventStreamCombines
import com.raquo.airstream.eventstream.SeqJoinEventStream

object EventStreamCompanionOps extends EventStreamCombines {

  def seq[A](streams: Seq[EventStream[A]]): EventStream[Seq[A]] = new SeqJoinEventStream[A](streams)

}
