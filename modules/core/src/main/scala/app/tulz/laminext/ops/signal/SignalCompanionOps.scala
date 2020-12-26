package app.tulz.laminext.ops.signal

import com.raquo.airstream.signal.SeqSignal
import com.raquo.airstream.signal.Signal
import com.raquo.airstream.signal.SignalCombines

object SignalCompanionOps extends SignalCombines {

  @inline def seq[A](signals: Seq[Signal[A]]): Signal[Seq[A]] = new SeqSignal[A](signals)

  @inline
  def combine[T1, T2](
    s1: Signal[T1],
    s2: Signal[T2]
  ): Signal[(T1, T2)] = s1.combineWith(s2)

}
