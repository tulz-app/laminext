package io.laminext.fetch

import org.scalajs.dom.Response
import scala.concurrent.duration.FiniteDuration

sealed abstract class FetchException(message: String) extends RuntimeException(message)

final case class FetchTimeout(timeout: FiniteDuration) extends FetchException(s"Request timeout: $timeout.")

final case class ResponseError(cause: Throwable, response: Response)
    extends FetchException(s"Failed to process the response: ${Option(cause).flatMap(e => Option(e.getMessage)).getOrElse("Underlying error unavailable.")}")

final case class FetchError(cause: Throwable)
    extends FetchException(
      s"Request failed. CORS issues or refused connection are common causes. ${Option(cause).flatMap(e => Option(e.getMessage)).getOrElse("Underlying error unavailable.")}"
    )
