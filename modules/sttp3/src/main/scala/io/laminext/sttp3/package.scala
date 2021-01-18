package io.laminext

import com.raquo.airstream.core.EventStream
import io.laminext.sttp3.ops.stream.StreamOfResponseOps
import sttp.client3.Response

package object sttp3 {

  object syntax {

    implicit def syntaxStreamOfResponse[E, R](
      underlying: EventStream[Response[Either[E, R]]]
    ): StreamOfResponseOps[E, R] = new StreamOfResponseOps[E, R](underlying)

  }

}
