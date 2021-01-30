package io.laminext.fetch

import org.scalajs.dom.experimental.Response
import io.circe.Error

class ResponseDecodeFailed(val error: Error, val response: Response) extends Throwable
