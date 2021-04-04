package io.laminext.fetch

import org.scalajs.dom.experimental.Response

class NonOkayResponse(val response: Response) extends Throwable
