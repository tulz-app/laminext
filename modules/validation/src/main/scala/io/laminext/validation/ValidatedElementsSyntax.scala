package io.laminext.validation

import cats.data.NonEmptyChain
import com.raquo.airstream.signal.Signal
import com.raquo.laminar.api.L._
import io.laminext.syntax.all._

trait ValidatedElementsSyntax {

  def validatedInput(
    validation: String => Either[NonEmptyChain[String], String],
    mods: Modifier[HtmlElement]*
  ): ValidatedInput[String] = {
    val el             = input(mods)
    val (value, error) = buildValueAndErrorSignals(el, el.valueSignal, validation)
    new ValidatedInput(el, value, error)
  }

  def validatedCheckBox(
    validation: Boolean => Either[NonEmptyChain[String], String],
    mods: Modifier[HtmlElement]*
  ): ValidatedInput[String] = {
    val el             = input(mods)
    val (value, error) = buildValueAndErrorSignals(el, el.checkedSignal, validation)
    new ValidatedInput(el, value, error)
  }

  def validatedTextArea(
    validation: String => Either[NonEmptyChain[String], String],
    mods: Modifier[HtmlElement]*
  ): ValidatedInput[String] = {
    val el             = textArea(mods)
    val (value, error) = buildValueAndErrorSignals(el, el.valueSignal, validation)
    new ValidatedInput(el, value, error)
  }

  private def buildValueAndErrorSignals[A](
    el: HtmlElement,
    value: Signal[A],
    validation: A => Either[NonEmptyChain[String], String]
  ): (Signal[Either[NonEmptyChain[String], String]], Signal[Option[NonEmptyChain[String]]]) = {
    val validatedValue = value.map(validation)

    val focusedAtLeastOnce = el.events(onBlur).mapToTrue.toSignal(false)

    val blurredAtLeastOnce =
      EventStream
        .merge(
          el.events(onBlur).mapToTrue,
          el.events(onFocus)
            .sample(
              Signal.combine(
                validatedValue,
                focusedAtLeastOnce
              )
            )
            .map { case (validatedValue, focusedAtLeastOnce) =>
              validatedValue.isLeft && focusedAtLeastOnce
            }
        )
        .toSignal(false)

    val errors = {
      Signal
        .combine(
          validatedValue,
          blurredAtLeastOnce
        )
        .map {
          case (Left(errors), true) => Some(errors)
          case _                    => Option.empty
        }
    }

    (validatedValue, errors)
  }

}

object ValidatedElementsSyntax extends ValidatedElementsSyntax
