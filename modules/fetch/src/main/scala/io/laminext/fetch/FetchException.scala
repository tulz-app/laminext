package io.laminext.fetch

import scala.concurrent.duration.FiniteDuration

sealed abstract class FetchException extends Throwable

final case class FetchTimeout(timeout: FiniteDuration) extends FetchException

final case class FetchError(cause: Any) extends FetchException

case object FetchAborted extends FetchException
