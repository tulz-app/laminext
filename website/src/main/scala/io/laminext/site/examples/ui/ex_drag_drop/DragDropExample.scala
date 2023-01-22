package io.laminext.site.examples.ui.ex_drag_drop

import com.raquo.laminar.CollectionCommand
import com.yurique.embedded.FileAsString
import io.laminext.site.examples.CodeExample
import io.laminext.ui.dnd.DraggingOver
import io.laminext.syntax.core._
import org.scalajs.dom.FileList

object DragDropExample
    extends CodeExample(
      id = "example-dragdrop",
      title = "Drag and Drop",
      description = FileAsString("description.md")
    )(() => {
      import com.raquo.laminar.api.L._
      import io.laminext.syntax.ui._

      div(
        cls := "p-4 bg-sky-100 space-y-8",
        div(
          cls := "flex space-x-4",
          (1 to 5).map { i =>
            div(cls := "p-2 bg-sky-200 rounded", s"drag me ${i}").draggable(s"drag me ${i}")
          }
        ),
        div(
          cls := "p-8 bg-sky-300 space-y-4 border-4 border-dashed rounded",
          div(
            cls := "flex items-center justify-center text-sky-600 text-xl font-black",
            "Drop here"
          )
        ).dropZone[String] { state =>
          List(
            cls.toggle("border-sky-300") <-- state.over.valueIs(DraggingOver.Out),
            cls.toggle("border-sky-400") <-- state.over.valueOneOf(DraggingOver.Over(true)),
            cls.toggle("border-rose-400") <-- state.over.valueOneOf(DraggingOver.Over(false)),
            children.command <-- state.drop.map { data =>
              CollectionCommand.Append(
                div(
                  cls := "p-2 bg-sky-800 text-sky-100",
                  data
                )
              )
            }
          )
        },
        div(
          cls := "p-8 bg-sky-300 space-y-4 border-4 border-dashed rounded",
          div(
            cls := "flex items-center justify-center text-sky-600 text-xl font-black",
            "Drop some files"
          )
        ).dropZone[FileList] { state =>
          List(
            cls.toggle("border-sky-300") <-- state.over.valueIs(DraggingOver.Out),
            cls.toggle("border-sky-400") <-- state.over.valueOneOf(DraggingOver.Over(true)),
            cls.toggle("border-rose-400") <-- state.over.valueOneOf(DraggingOver.Over(false)),
            children.command <-- state.drop.map { data =>
              CollectionCommand.Append(
                div(
                  cls := "p-2 bg-sky-800 text-sky-100",
                  data.map(_.name).mkString("Files: ", ", ", "")
                )
              )
            }
          )

        },
      )
    })
