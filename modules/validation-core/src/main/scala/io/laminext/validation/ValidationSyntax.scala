package io.laminext.validation

import com.raquo.laminar.api.L._
import io.laminext.validation.ops.element.InputValidationOps
import io.laminext.validation.ops.element.TextAreaValidationOps
import io.laminext.validation.ops.EventStreamValidationOps
import io.laminext.validation.ops.SignalValidationOps

trait ValidationSyntax {

  implicit def syntaxSignalValidation[A](signal: Signal[A]): SignalValidationOps[A] =
    new SignalValidationOps[A](signal)

  implicit def syntaxEventStreamValidation[A](stream: EventStream[A]): EventStreamValidationOps[A] =
    new EventStreamValidationOps[A](stream)

  implicit def syntaxInputValidatedValue(el: Input): InputValidationOps =
    new InputValidationOps(el)

  implicit def syntaxTextAreaValidatedValue(el: TextArea): TextAreaValidationOps =
    new TextAreaValidationOps(el)

}
