package io.laminext.core

import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ChildNode
import com.raquo.laminar.nodes.ReactiveElement

import scala.collection.immutable
import scala.concurrent.ExecutionContext
import scala.concurrent.Future

private[laminext] object FutureChildrenReceiver {

  @inline def <--(future: Future[immutable.Seq[ChildNode.Base]])(implicit ec: ExecutionContext): Inserter =
    children <-- EventStream.fromFuture(future)

}
