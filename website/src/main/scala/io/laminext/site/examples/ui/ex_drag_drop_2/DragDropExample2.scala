package io.laminext.site.examples.ui.ex_drag_drop_2

import com.yurique.embedded.FileAsString
import io.laminext.site.examples.CodeExample

object DragDropExample2
    extends CodeExample(
      id = "example-dragdrop-2",
      title = "Drag and Drop 2",
      description = FileAsString("description.md")
    )(() => {
      import com.raquo.laminar.api.L._
      import io.laminext.syntax.ui._
      import io.laminext.ui.dnd.DraggingOver
      import io.laminext.syntax.core._

      val listVar  = Var(List.empty[String])
      val dragging = Var(false)
      val insert   = Var(false)

      div(
        cls := "p-4 bg-sky-100 space-y-8",
        div(
          cls := "flex space-x-4",
          (1 to 5).map { i =>
            div(
              cls := "p-2 bg-sky-200 rounded",
              s"drag me ${i}",
              onDragStart.mapToTrue --> dragging,
              onDragEnd.mapToFalse --> dragging,
            ).draggable(s"drag me ${i}")
          }
        ),
        div(
          cls := "p-8 bg-sky-300 space-y-2 rounded",
          children <-- listVar.signal.map(_.zipWithIndex).split(_._2) { (index, _, s) =>
            div().dropZone[String] { state =>
              List(
                state.drop --> listVar.updater[String] { (list, item) =>
                  list.take(index) ++ List(item) ++ list.drop(index)
                },
                state.enter --> insert,
                state.leave.mapToFalse --> insert,
                div(
                  cls := "py-1 border-t-4 border-dashed",
                  cls.toggle("border-sky-400") <-- state.over.valueIs(DraggingOver.Out),
                  cls.toggle("border-sky-700") <-- state.over.valueIs(DraggingOver.Over(true)),
                  cls.toggle("border-rose-700") <-- state.over.valueIs(DraggingOver.Over(false)),
                  cls.toggle("invisible") <-- state.over.valueIs(DraggingOver.Out),
                ),
                div(
                  cls := "p-2 bg-sky-800 text-sky-100",
                  child.text <-- s.map(_._1)
                ),
              )
            }
          },
          div(
            cls := "p-2 bg-sky-300 space-y-4 h-12 border-4 border-dashed rounded",
            cls.toggle("hidden") <-- !dragging.signal,
          ).dropZone[String] { state =>
            List(
              state.drop --> listVar.updater[String](_ :+ _),
              cls.toggle("border-sky-400") <-- state.over.valueIs(DraggingOver.Out),
              cls.toggle("border-sky-700") <-- state.over.valueIs(DraggingOver.Over(true)),
              cls.toggle("border-rose-700") <-- state.over.valueIs(DraggingOver.Over(false)),
            )
          }
        )
      )
    })
