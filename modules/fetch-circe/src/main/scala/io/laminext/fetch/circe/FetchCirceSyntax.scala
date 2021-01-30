package io.laminext.fetch.circe

import io.circe._

trait FetchCirceSyntax {

  implicit def jsonRequestBody[A](value: A)(implicit encoder: Encoder[A]): ToRequestBody =
    new JsonToRequestBody(encoder(value).noSpaces)

  implicit def fetchEventStreamBuilderSyntaxCirce(b: FetchEventStreamBuilder): FetchEventStreamBuilderCirceOps =
    new FetchEventStreamBuilderCirceOps(b)

}
