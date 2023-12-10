package io.laminext.core

import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ChildNode
import com.raquo.laminar.nodes.ReactiveElement
import org.scalajs.dom

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

private[laminext] object FutureChildReceiver {

  val maybe: MaybeFutureChildReceiver.type = MaybeFutureChildReceiver

  @inline def <--(future: Future[ChildNode.Base])(implicit ec: ExecutionContext): Inserter =
    child <-- EventStream.fromFuture(future)

}
