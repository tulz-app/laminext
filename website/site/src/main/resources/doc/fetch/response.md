## Underlying response object

The underlying `fetch` API initially provides the response as a `org.scalajs.dom.experimental.Response`.

It contains the basic information about the response available before reading the response body:

* `type: org.scalajs.dom.experimental.ResponseType`
* `url: String`
* `status: Int`
* `statusText: String`
* `ok: Boolean`
* `headers: org.scalajs.dom.experimental.Headers`

and the `body: org.scalajs.dom.experimental.ReadableStream[Uint8Array]`.

`Response` also provides methods to consume the response body in one of the following forms:

* `response.text()`
* `response.json()`
* `response.blob()`
* `response.arrayBuffer()`

The above methods return a `Promise` and `FetchEventStreamBuilder` provides functions
to specify the type of response you're expecting and want to work with (dealing with the `Promise`s under the hood).

You get an `EventStream` of `FetchResponse[A]`, which contains the same basic information about the response mentioned earlier
and the `data` of type `A` which depends on which response reader you specify:

### Raw response

```scala
import io.laminext.fetch._
import com.raquo.airstream.eventstream.EventStream
import org.scalajs.dom.experimental.Response

val s: EventStream[FetchResponse[Response]] = Fetch.post("https://...").raw 
```

### Text 

```scala
import io.laminext.fetch._
import com.raquo.airstream.eventstream.EventStream

val s: EventStream[FetchResponse[String]] = Fetch.post("https://...").text 
```

### JSON (js-native) 

```scala
import io.laminext.fetch._
import com.raquo.airstream.eventstream.EventStream

val s: EventStream[FetchResponse[Any]] = Fetch.post("https://...").json 
```

### Blob 

```scala
import io.laminext.fetch._
import com.raquo.airstream.eventstream.EventStream
import org.scalajs.dom

val s: EventStream[FetchResponse[dom.Blob]] = Fetch.post("https://...").blob 
```

### Array buffer 

```scala
import io.laminext.fetch._
import com.raquo.airstream.eventstream.EventStream
import scala.scalajs.js.typedarray.ArrayBuffer

val s: EventStream[FetchResponse[ArrayBuffer]] = Fetch.post("https://...").arrayBuffer 
```

### Custom

You can provide a custom response reader: defined by a `Response => Promise[A]` function.

```scala
import io.laminext.fetch._
import com.raquo.airstream.eventstream.EventStream
import org.scalajs.dom.experimental.Response

val myReader: Response => MyData = ??? 

val s: EventStream[FetchResponse[MyData]] = Fetch.post("https://...").build(myReader) 
```

### Circe support

If you use circe, there's a `fetch-circe` module available. See [Circe support](/fetch/circe).
