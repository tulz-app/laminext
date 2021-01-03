package io.laminext

import io.laminext.validation.ops.signal.SignalOfBooleanValidationOps
import io.laminext.validation.ops.signal.SignalOfStringValidationOps
import io.laminext.validation.ops.stream.StreamOfBooleanValidationOps
import io.laminext.validation.ops.stream.StreamOfStringValidationOps
import cats.data.NonEmptyChain
import com.raquo.laminar.api.L._

package object validation extends ValidatedElementsSyntax {

  type ValidatedValue[T] = Either[NonEmptyChain[String], T]
  type Validation[T]     = T => Either[cats.data.NonEmptyChain[String], T]

  object syntax extends ValidatedElementsSyntax {

    implicit def syntaxStreamOfStringValidation(stream: EventStream[String]): StreamOfStringValidationOps =
      new StreamOfStringValidationOps(stream)

    implicit def syntaxSignalOfStringValidation(signal: Signal[String]): SignalOfStringValidationOps =
      new SignalOfStringValidationOps(signal)

    implicit def syntaxStreamOfBooleanValidation(stream: EventStream[Boolean]): StreamOfBooleanValidationOps =
      new StreamOfBooleanValidationOps(stream)

    implicit def syntaxSignalOfBooleanValidation(signal: Signal[Boolean]): SignalOfBooleanValidationOps =
      new SignalOfBooleanValidationOps(signal)

    val V: Validations.type = Validations

  }

}
