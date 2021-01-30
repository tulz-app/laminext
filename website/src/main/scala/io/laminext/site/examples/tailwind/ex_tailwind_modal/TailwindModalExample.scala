package io.laminext.site.examples.tailwind.ex_tailwind_modal

import com.yurique.embedded.FileAsString
import io.laminext.site.examples.CodeExample
import io.laminext.site.ExampleModalContent
import io.laminext.tailwind.modal.ModalContent

object TailwindModalExample
    extends CodeExample(
      id = "example-modal",
      title = "Modal",
      description = FileAsString("description.md")
    )(() => {
      import com.raquo.laminar.api.L._
      import io.laminext.syntax.tailwind._

      /* <focus> */
      /*
       * at the start of the application:
       * Modal.initialize()
       *
       * ExampleModalContent.modalContent is defined like the following:
       *
       * object ExampleModalContent {
       *   val modalContent: Var[Option[tailwind.ModalContent]] = Var[Option[ModalContent]](Option.empty)
       * }
       *
       * Up in the dom, modal container is rendered like this:
       *
       * TW.modal(ExampleModalContent.modalContent.signal)
       * */
      /* </focus> */

      div(
        cls := "p-4",
        button.btn.md.fill.blue(
          "Show Modal",
          onClick --> { _ =>
            /* <focus> */
            ExampleModalContent.modalContent.writer.onNext(
              Some(
                ModalContent(
                  div(
                    div(
                      cls := "p-8",
                      "I'm in the modal!",
                    ),
                    div(
                      cls := "p-8",
                      button.btn.md.outline.blue(
                        "close me",
                        onClick.mapTo(None) --> ExampleModalContent.modalContent.writer
                      )
                    )
                  ),
                  Some(Observer(_ => ExampleModalContent.modalContent.writer.onNext(None)))
                )
              )
            )
            /* </focus> */
          }
        )
      )
    })
