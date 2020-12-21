package app.tulz.tailwind

import com.raquo.laminar.api.L._
import app.tulz.tailwind.theme.Theme
import app.tulz.ui.transition.Transition
import app.tulz.ui.transition.TransitionConfig
import com.raquo.laminar.nodes.ReactiveHtmlElement

trait TransitionSyntax {

  object transition {

    @inline def opacityAndScale(show: Signal[Boolean]): Modifier[ReactiveHtmlElement.Base] =
      custom(show, Theme.current.transition.opacityAndScale)

    @inline def opacityAndScaleLong(show: Signal[Boolean]): Modifier[ReactiveHtmlElement.Base] =
      custom(show, Theme.current.transition.opacityAndScaleLong)

    @inline def opacity(show: Signal[Boolean]): Modifier[ReactiveHtmlElement.Base] =
      custom(show, Theme.current.transition.opacity)

    @inline def opacityLong(show: Signal[Boolean]): Modifier[ReactiveHtmlElement.Base] =
      custom(show, Theme.current.transition.opacityLong)

    @inline def apply(show: Signal[Boolean]): Modifier[ReactiveHtmlElement.Base] = Transition(show, Theme.current.transition.default)

    @inline def custom(
      show: Signal[Boolean],
      config: TransitionConfig
    ): Modifier[ReactiveHtmlElement.Base] = Transition(show, config)

  }

}
