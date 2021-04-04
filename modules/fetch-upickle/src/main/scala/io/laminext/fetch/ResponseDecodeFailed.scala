package io.laminext.fetch

import org.scalajs.dom.experimental.Response

class ResponseDecodeFailed(val error: Throwable, val response: Response) extends Throwable
