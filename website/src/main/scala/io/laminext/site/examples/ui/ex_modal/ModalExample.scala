package io.laminext.site.examples.ui.ex_modal

import com.yurique.embedded.FileAsString
import io.laminext.site.examples.CodeExample

object ModalExample
    extends CodeExample(
      id = "example-modal",
      title = "Modal",
      description = FileAsString("description.md")
    )(() => {
      import com.raquo.laminar.api.L._
      import io.laminext.syntax.ui._
      import io.laminext.ui.theme._

      /* <focus> */
      val modalStyling = ModalConfig(
        container = cls("fixed inset-0 z-50"),
        overlayTransition = TransitionConfig(
          nonHidden = "hidden md:block md:absolute md:inset-0 bg-gray-900",
          showing = "bg-opacity-75",
          inTransition = "hidden md:absolute md:transition-opacity motion-reduce:transition-none",
          enter = "duration-100 ease-out",
          enterFrom = "bg-opacity-0",
          enterTo = "bg-opacity-75",
          leave = "duration-150 ease-in",
          leaveFrom = "bg-opacity-75",
          leaveTo = "bg-opacity-0"
        ),
        contentWrapTransition = TransitionConfig(
          nonHidden =
            "w-full h-screen pb-20 overflow-y-scroll bg-white md:absolute md:top-1/3 left-1/2 md:-translate-y-1/3 md:-translate-x-1/2 md:max-w-4xl md:h-auto md:pb-0 md:rounded-lg md:shadow-xl",
          showing = "transform",
          inTransition = "transform transition-all motion-reduce:transition-none motion-reduce:transform-none",
          enter = "duration-100 ease-out",
          enterFrom = "scale-75",
          enterTo = "scale-100",
          leave = "duration-150 ease-in",
          leaveFrom = "scale-100",
          leaveTo = "scale-75"
        ),
        contentWrapInner = cls("md:mx-auto")
      )
      /* </focus> */

      /* <focus> */
      val modalContent = Var[Option[Element]](Option.empty)
      /* </focus> */
      div(
        cls := "p-4",
        button(
          cls := "btn-md-fill-blue",
          "Show Modal",
          onClick --> { _ =>
            /* <focus> */
            modalContent.writer.onNext(
              Some(
                div(
                  div(
                    cls := "p-8",
                    "I'm in the modal!",
                  ),
                  div(
                    cls := "p-8",
                    button(
                      cls := "btn-md-outline-blue",
                      "close me",
                      onClick.mapTo(None) --> modalContent.writer
                    )
                  )
                )
              )
            )
            /* </focus> */
          }
        ),
        /* <focus> */
        modal(styling = modalStyling, closeObserver = Observer(_ => modalContent.writer.onNext(None))) {
          modalContent.signal
        },
        /* </focus> */
      )
    })
