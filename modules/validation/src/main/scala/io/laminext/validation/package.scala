package io.laminext

import io.laminext.validation.ops.signal.SignalValidationOps
import io.laminext.validation.ops.stream.EventStreamValidationOps
import com.raquo.laminar.api.L._
import io.laminext.validation.ops.element.InputValidationOps
import io.laminext.validation.ops.element.TextAreaValidationOps
import io.laminext.validation.ops.validation.ValidationOps

package object validation {

  type ValidatedValue[T] = Either[ValidationError, T]
  type ValidationError   = Seq[String]
  type Validation[T]     = T => ValidatedValue[T]

  object syntax {

    implicit def syntaxSignalValidation[A](signal: Signal[A]): SignalValidationOps[A] =
      new SignalValidationOps[A](signal)

    implicit def syntaxEventStreamValidation[A](stream: EventStream[A]): EventStreamValidationOps[A] =
      new EventStreamValidationOps[A](stream)

    implicit def syntaxInputValidatedValue(el: Input): InputValidationOps =
      new InputValidationOps(el)

    implicit def syntaxTextAreaValidatedValue(el: TextArea): TextAreaValidationOps =
      new TextAreaValidationOps(el)

    implicit def syntaxValidations[A](v: Validation[A]): ValidationOps[A] =
      new ValidationOps[A](v)

    val V: Validations.type = Validations

  }

}
