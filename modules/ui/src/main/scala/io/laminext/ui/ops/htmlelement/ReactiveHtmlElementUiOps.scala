package io.laminext.ui.ops.htmlelement

import com.raquo.laminar.api.L._
import com.raquo.laminar.api.L
import com.raquo.laminar.nodes.ReactiveHtmlElement
import io.laminext.ui.dnd.DragDropDecoder
import io.laminext.ui.dnd.DragDropEncoder
import io.laminext.ui.dnd.DraggingOver
import io.laminext.ui.dnd.DropZoneElement
import io.laminext.ui.dnd.DropZoneState
import org.scalajs.dom

class ReactiveHtmlElementUiOps[T <: dom.html.Element](el: ReactiveHtmlElement[T]) {

  def dropZone[A: DragDropDecoder](mods: DropZoneState[A] => Seq[Modifier[ReactiveHtmlElement[T]]]): DropZoneElement[T, A] = {
    var enters = 0
    val enter  = new EventBus[Boolean]
    val leave  = new EventBus[Unit]

    val draggingOver = Var[DraggingOver](DraggingOver.Out)
    val drops        = new EventBus[A]

    val state = new DropZoneState[A](
      enter = enter.events,
      leave = leave.events,
      drop = drops.events,
      over = draggingOver.signal,
    )

    val decoder = DragDropDecoder[A]

    new DropZoneElement(
      el = el.amend(
        onDragEnter.preventDefault --> { ev =>
          if (enters == 0) {
            enter.emit(decoder.willAccept(ev))
            draggingOver.set(DraggingOver.Over(decoder.willAccept(ev)))
          }
          enters += 1
        },
        onDragLeave.preventDefault --> { ev =>
          enters -= 1
          if (enters == 0) {
            enters = 0
            leave.emit(())
            draggingOver.set(DraggingOver.Out)
          }
        },
        onDragOver.preventDefault --> Observer.empty,
        onDrop.preventDefault
          .map { ev =>
            enters = 0
            draggingOver.set(DraggingOver.Out)
            decoder.getData(ev).toOption
          }
          .collect { case Some(data) => data } --> drops,
        mods(state),
      ),
      enter = enter.events,
      leave = leave.events,
      drop = drops.events,
      over = draggingOver.signal,
    )
  }

  def draggable[A: DragDropEncoder](value: A): ReactiveHtmlElement[T] = draggable[A](Val(value))

  def draggable[A: DragDropEncoder](value: Signal[A]): ReactiveHtmlElement[T] = {
    el.amend(
      L.draggable := true,
      onDragStart.compose(_.withCurrentValueOf(value)) --> { case (ev, value) =>
        DragDropEncoder[A].setData(ev, value)
      }
    )
  }

}
