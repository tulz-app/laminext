package io.laminext

import io.circe._
import org.scalajs.dom
import org.scalajs.dom.experimental.BodyInit

import scala.scalajs.js

package object fetch {

  implicit def jsonRequestBody[A](
    value: A
  )(implicit encoder: Encoder[A]): RequestBody = () => encoder(value).noSpaces

  implicit def fetchEventStreamBuilderSyntax(b: FetchEventStreamBuilder): FetchEventStreamBuilderOps = new FetchEventStreamBuilderOps(b)

}
