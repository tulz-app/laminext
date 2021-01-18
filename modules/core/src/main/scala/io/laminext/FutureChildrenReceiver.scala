package io.laminext

import com.raquo.laminar.api.L._
import com.raquo.airstream.core.EventStream
import com.raquo.laminar.modifiers.Inserter
import com.raquo.laminar.nodes.ReactiveElement

import scala.concurrent.Future

object FutureChildrenReceiver {

  @inline def <--(future: Future[Children]): Inserter[ReactiveElement.Base] =
    children <-- EventStream.fromFuture(future)

}
