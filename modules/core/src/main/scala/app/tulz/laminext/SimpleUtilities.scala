package app.tulz.laminext

import com.raquo.airstream.core.Observer
import com.raquo.airstream.eventbus.EventBus
import com.raquo.airstream.eventstream.EventStream
import com.raquo.laminar.api.L._

trait SimpleUtilities {

  def withBus[T](body: Observer[T] => Unit): EventStream[T] = {
    val bus = new EventBus[T]
    body(bus.writer)
    bus.events
  }

  def createTrigger(): (() => Unit, EventStream[Unit]) = {
    val bus = new EventBus[Unit]()
    (
      () => {
        bus.writer.onNext((): Unit)
      },
      bus.events
    )
  }

  def createStream[T](body: WriteBus[T] => Unit): EventStream[T] = {
    val bus = new EventBus[T]
    body(bus.writer)
    bus.events
  }

}
