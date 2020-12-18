package app.tulz.laminext

import com.raquo.airstream.signal.Signal

object ToggleableSignal {

  def apply(initial: Boolean): (Signal[Boolean], () => Unit) = {
    val (s, update) = UpdatableSignal(initial)
    s -> (() => {
      update.onNext(!_)
    })
  }

}
