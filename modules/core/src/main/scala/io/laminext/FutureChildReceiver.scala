package io.laminext

import com.raquo.airstream.eventstream.EventStream
import com.raquo.laminar.modifiers.ChildInserter
import com.raquo.laminar.modifiers.Inserter
import com.raquo.laminar.nodes.ChildNode
import com.raquo.laminar.nodes.ReactiveElement
import org.scalajs.dom

import scala.concurrent.Future

object FutureChildReceiver {

  val maybe: MaybeFutureChildReceiver.type = MaybeFutureChildReceiver

  def <--($node: Future[ChildNode[dom.Node]]): Inserter[ReactiveElement.Base] =
    ChildInserter[ReactiveElement.Base](_ => EventStream.fromFuture($node), initialInsertContext = None)

}
