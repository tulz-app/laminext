package io.laminext.validation
package components

import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveHtmlElement
import io.laminext.base.ComponentBase
import io.laminext.syntax.core._
import org.scalajs.dom

class ValidatedElement[+R <: dom.html.Element, A, Err, Out](
  val el: ReactiveHtmlElement[R],
  val value: Signal[A],
  val validatedValue: Signal[ValidatedValue[Err, Out]],
  val validationError: Signal[Option[Err]],
  val resetError: Observer[Unit]
) extends ComponentBase[R]

object ValidatedElement {

  def apply[R <: dom.html.Element, A, Err, Out](
    el: ReactiveHtmlElement[R],
    value: Signal[A],
    validation: Validation[A, Err, Out]
  ): ValidatedElement[R, A, Err, Out] = {
    val validatedValue = value.map(validation)
    val resetBus       = new EventBus[Unit]()

    val blurredAtLeastOnce =
      EventStream
        .merge(
          el.events(onBlur.mapToTrue),
          resetBus.events.mapToFalse
        )
        .scanLeft(false)((_, value) => value)

    val shouldShowError =
      EventStream
        .merge(
          resetBus.events.mapToFalse,
          el.events(onBlur).mapToTrue,
          el.events(onFocus)
            .sample(
              Signal.combine(validatedValue, blurredAtLeastOnce)
            )
            .map { case (validatedValue, focusedAtLeastOnce) =>
              validatedValue.isLeft && focusedAtLeastOnce
            }
        )
        .toSignal(false)

    val error =
      Signal
        .combine(validatedValue, shouldShowError)
        .map {
          case (Left(errors), true) => Some(errors)
          case _                    => Option.empty
        }

    new ValidatedElement[R, A, Err, Out](
      el = el,
      value = value,
      validatedValue = validatedValue,
      validationError = error,
      resetError = resetBus.writer
    )
  }

}
