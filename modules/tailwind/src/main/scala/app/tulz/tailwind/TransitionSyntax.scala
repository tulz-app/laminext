package app.tulz.tailwind

import theme.Theme
import com.raquo.laminar.api.L._
import app.tulz.ui.transition.Transition
import app.tulz.ui.transition.TransitionConfig
import com.raquo.laminar.nodes.ReactiveHtmlElement

trait TransitionSyntax {

  object transition {

    @inline def opacityAndScale(show: Signal[Boolean]): Modifier[ReactiveHtmlElement.Base] =
      apply(show, theme.Transition.opacityAndScale)

    @inline def opacity(show: Signal[Boolean]): Modifier[ReactiveHtmlElement.Base] =
      apply(show, theme.Transition.opacity)

    @inline def apply(
      show: Signal[Boolean],
      config: TransitionConfig = Theme.current.transition.default
    ): Modifier[ReactiveHtmlElement.Base] = Transition(show, config)

  }

}

object TransitionSyntax extends TransitionSyntax
