package io.laminext.fetch
package ops

class FetchEventStreamBuilderOps(underlying: FetchEventStreamBuilder) {

  def body(body: ToRequestBody): FetchEventStreamBuilder = underlying.setBody(body)

}
