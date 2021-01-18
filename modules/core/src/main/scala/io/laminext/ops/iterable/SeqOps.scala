package io.laminext.ops.iterable

import scala.collection.AbstractIterator
import scala.collection.AbstractView
import scala.collection.BuildFrom
import scala.collection.generic.IsSeq

final class SeqOps[Repr, S <: IsSeq[Repr]](coll: Repr, val seq: S) {

  def join[B >: seq.A, That](
    sep: () => B
  )(implicit bf: BuildFrom[Repr, B, That]): That = {
    val seqOps = seq(coll)
    bf.fromSpecific(coll)(new AbstractView[B] {
      def iterator: AbstractIterator[B] = new AbstractIterator[B] {
        private val it              = seqOps.iterator
        private var intersperseNext = false
        def hasNext: Boolean        = intersperseNext || it.hasNext
        def next(): B = {
          val elem = if (intersperseNext) sep() else it.next()
          intersperseNext = !intersperseNext && it.hasNext
          elem
        }
      }
    })
  }

}
