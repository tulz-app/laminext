package io.laminext

import com.raquo.laminar.api.L._
import com.raquo.airstream.eventstream.EventStream
import com.raquo.laminar.modifiers.ChildInserter
import com.raquo.laminar.modifiers.Inserter
import com.raquo.laminar.nodes.ChildNode
import com.raquo.laminar.nodes.ReactiveElement
import org.scalajs.dom
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future

object MaybeFutureChildReceiver {

  def <--($nodes: Future[Option[ChildNode[dom.Node]]]): Inserter[ReactiveElement.Base] = {
    val emptyNode = new CommentNode("")
    ChildInserter[ReactiveElement.Base](_ => EventStream.fromFuture($nodes.map(_.getOrElse(emptyNode))), initialInsertContext = None)
  }

}
