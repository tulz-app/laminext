package io.laminext.validation

import cats.data.NonEmptyChain
import com.raquo.laminar.api.L._

class ValidatedInput[T](
  val element: HtmlElement,
  val value: Signal[Either[NonEmptyChain[String], T]],
  val error: Signal[Option[NonEmptyChain[String]]]
)
