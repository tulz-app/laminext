package io.laminext.fetch
package ziojson

import zio.json._

trait FetchZioJsonSyntax {

  implicit def jsonRequestBody[A](value: A)(implicit encoder: JsonEncoder[A]): ToRequestBody =
    new JsonToRequestBody(value.toJson)

  implicit def fetchEventStreamBuilderSyntaxZioJson(b: FetchEventStreamBuilder): FetchEventStreamBuilderZioJsonOps =
    new FetchEventStreamBuilderZioJsonOps(b)

}
