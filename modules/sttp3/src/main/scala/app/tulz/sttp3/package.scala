package app.tulz

import app.tulz.sttp3.ops.stream.StreamOfResponseOps
import com.raquo.airstream.eventstream.EventStream
import sttp.client3.Response

package object sttp3 {

  implicit def syntaxStreamOfResponse[E, R](underlying: EventStream[Response[Either[E, R]]]): StreamOfResponseOps[E, R] = new StreamOfResponseOps[E, R](underlying)

}
