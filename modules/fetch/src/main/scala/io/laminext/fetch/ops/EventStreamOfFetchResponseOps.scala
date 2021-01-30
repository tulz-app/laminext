package io.laminext.fetch.ops

import com.raquo.laminar.api.L._
import io.laminext.fetch.FetchResponse

class EventStreamOfFetchResponseOps[A](underlying: EventStream[FetchResponse[A]]) {

  def mapData[B](project: A => B): EventStream[B] =
    underlying.map(r => project(r.data))

  def data: EventStream[A] =
    underlying.map(_.data)

  def okay: EventStream[Boolean] =
    underlying.map(_.ok)

  def status: EventStream[Int] =
    underlying.map(_.status)

}
