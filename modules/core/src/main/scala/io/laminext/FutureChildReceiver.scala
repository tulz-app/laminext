package io.laminext

import com.raquo.laminar.api.L._
import com.raquo.airstream.core.EventStream
import com.raquo.laminar.modifiers.Inserter
import com.raquo.laminar.nodes.ChildNode
import com.raquo.laminar.nodes.ReactiveElement
import org.scalajs.dom

import scala.concurrent.Future

object FutureChildReceiver {

  val maybe: MaybeFutureChildReceiver.type = MaybeFutureChildReceiver

  @inline def <--(future: Future[ChildNode[dom.Node]]): Inserter[ReactiveElement.Base] =
    child <-- EventStream.fromFuture(future)

}
