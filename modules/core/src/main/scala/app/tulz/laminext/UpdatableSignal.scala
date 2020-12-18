package app.tulz.laminext

import com.raquo.airstream.core.Observer
import com.raquo.airstream.eventbus.EventBus
import com.raquo.airstream.signal.Signal

object UpdatableSignal {

  def apply[A](initial: A): (Signal[A], Observer[A => A]) = {
    val bus = new EventBus[A => A]
    bus.events.foldLeft(initial) { case (current, project) =>
      project(current)
    } -> bus.writer
  }

}
