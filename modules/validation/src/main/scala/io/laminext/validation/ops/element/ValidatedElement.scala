package io.laminext.validation
package ops.element

import com.raquo.airstream.core.Signal
import com.raquo.airstream.core.EventStream
import com.raquo.laminar.api.L.onBlur
import com.raquo.laminar.api.L.onFocus
import com.raquo.laminar.nodes.ReactiveHtmlElement
import io.laminext.syntax.core._
import org.scalajs.dom

class ValidatedElement[+Ref <: dom.html.Element, A](
  val el: ReactiveHtmlElement[Ref],
  val validatedValue: Signal[ValidatedValue[A]],
  val validationError: Signal[Option[ValidationError]]
)

object ValidatedElement {

  @inline implicit def validatedElementToReactiveHtmlElement[Ref <: dom.html.Element, A](
    validated: ValidatedElement[Ref, A]
  ): ReactiveHtmlElement[Ref] = validated.el

  def apply[Ref <: dom.html.Element, A](
    el: ReactiveHtmlElement[Ref],
    value: Signal[A],
    validation: Validation[A]
  ): ValidatedElement[Ref, A] = {
    val validatedValue = value.map(validation)

    val focusedAtLeastOnce = el.events(onBlur).foldLeft(false)((_, _) => true)

    val blurredAtLeastOnce =
      EventStream
        .merge(
          el.events(onBlur).mapToTrue,
          el.events(onFocus)
            .sample(
              Signal.combine(validatedValue, focusedAtLeastOnce)
            )
            .map { case (validatedValue, focusedAtLeastOnce) =>
              validatedValue.isLeft && focusedAtLeastOnce
            }
        )
        .toSignal(false)

    val error =
      Signal
        .combine(validatedValue, blurredAtLeastOnce)
        .map {
          case (Left(errors), true) => Some(errors)
          case _                    => Option.empty
        }

    new ValidatedElement[Ref, A](el, validatedValue, error)
  }

}
