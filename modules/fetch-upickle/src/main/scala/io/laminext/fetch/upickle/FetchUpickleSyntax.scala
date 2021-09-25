package io.laminext.fetch
package upickle

import _root_.upickle.default._

trait FetchUpickleSyntax {

  implicit def jsonRequestBody[A](value: A)(implicit encoder: Writer[A]): ToRequestBody                          =
    new JsonToRequestBody(write(value))

  implicit def fetchEventStreamBuilderSyntaxCirce(b: FetchEventStreamBuilder): FetchEventStreamBuilderUpickleOps =
    new FetchEventStreamBuilderUpickleOps(b)

}
