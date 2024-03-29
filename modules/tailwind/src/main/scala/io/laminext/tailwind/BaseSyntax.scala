package io.laminext.tailwind

import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveHtmlElement
import com.raquo.laminar.nodes.ReactiveSvgElement
import io.laminext.tailwind.ops.htmlelement.ReactiveHtmlElementTailwindOps
import io.laminext.tailwind.ops.signal.SignalOfBooleanTailwindOps
import io.laminext.tailwind.ops.svgelement.ReactiveSvgElementTailwindOps
import io.laminext.tailwind.progressbar.ProgressBar
import io.laminext.tailwind.theme.Theme
import io.laminext.ui.animation.Animation
import org.scalajs.dom
import org.scalajs.dom.html

trait BaseSyntax {

  val Modal: io.laminext.tailwind.modal.Modal.type = io.laminext.tailwind.modal.Modal
  type ModalContent = io.laminext.tailwind.modal.ModalContent
  val ModalContent: io.laminext.tailwind.modal.ModalContent.type = io.laminext.tailwind.modal.ModalContent

  object TW {

    @inline def progressBar(
      progress: Signal[Double],
      hidden: Signal[Boolean] = Val(false),
      thm: theme.ProgressBar = theme.Theme.current.progressBar
    ): ReactiveHtmlElement[html.Div] = ProgressBar(progress, hidden, thm)

    @inline def modal(
      content: Signal[Option[ModalContent]],
      config: theme.Modal = Theme.current.modal
    ): Element = Modal(content, config)

    object transition extends TransitionSyntax

    @inline def animation(
      animationClass: Signal[Option[String]],
      iterations: Int = 1,
      endObserver: Observer[String] = Observer.empty
    ): Modifier[ReactiveHtmlElement.Base] = Animation(animationClass, iterations, endObserver)

  }

  implicit def syntaxReactiveHtmlElementTailwind[T <: dom.html.Element](
    el: ReactiveHtmlElement[T]
  ): ReactiveHtmlElementTailwindOps[T] = new ReactiveHtmlElementTailwindOps[T](el)

  implicit def syntaxReactiveSvgElementTailwind[T <: dom.svg.Element](
    el: ReactiveSvgElement[T]
  ): ReactiveSvgElementTailwindOps[T] = new ReactiveSvgElementTailwindOps[T](el)

  implicit def syntaxSignalOfBooleanTailwind(s: Signal[Boolean]): SignalOfBooleanTailwindOps = new SignalOfBooleanTailwindOps(s)

}
