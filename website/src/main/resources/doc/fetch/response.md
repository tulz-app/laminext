## Underlying response object

The underlying Fetch API initially provides the response as a `org.scalajs.dom.Response`.

It contains the basic information about the response available before reading the response body:

* `type: org.scalajs.dom.ResponseType`
* `url: String`
* `status: Int`
* `statusText: String`
* `ok: Boolean`
* `headers: org.scalajs.dom.Headers`

and the `body: org.scalajs.dom.ReadableStream[Uint8Array]`.

`Response` also provides methods to consume the response body in one of the following forms:

* `response.text()`
* `response.json()`
* `response.blob()`
* `response.arrayBuffer()`

The above methods return a `Promise` and `FetchEventStreamBuilder` provides functions
to specify the type of response you're expecting and want to work with (dealing with the `Promise`s under the hood).

You get an `EventStream` of `FetchResponse[A]`, which contains the same basic information about the response mentioned earlier
and the `data` of type `A` which depends on which response reader you specify.

Another option is to get a `Future` of `FetchResponse[A]` providing the same data when the `Future` completes.

### Raw response

```scala
import com.raquo.laminar.api.L._
import io.laminext.fetch._
import org.scalajs.dom.Response
import scala.concurrent.Future

val s: EventStream[FetchResponse[Response]] = Fetch.post("https://...").raw
// OR
val f: Future[FetchResponse[Response]] = Fetch.post("https://...").future.raw() 
```

### Text 

```scala
import com.raquo.laminar.api.L._
import io.laminext.fetch._
import scala.concurrent.Future

val s: EventStream[FetchResponse[String]] = Fetch.post("https://...").text
// OR
val f: Future[FetchResponse[String]] = Fetch.post("https://...").future.text()
```

### JSON (js-native) 

```scala
import com.raquo.laminar.api.L._
import io.laminext.fetch._
import scala.concurrent.Future

val s: EventStream[FetchResponse[Any]] = Fetch.post("https://...").json 
// OR
val f: Future[FetchResponse[Any]] = Fetch.post("https://...").future.json() 
```

### Blob 

```scala
import com.raquo.laminar.api.L._
import io.laminext.fetch._
import org.scalajs.dom
import scala.concurrent.Future

val s: EventStream[FetchResponse[dom.Blob]] = Fetch.post("https://...").blob
// OR
val f: Future[FetchResponse[dom.Blob]] = Fetch.post("https://...").future.blob() 
```

### Array buffer 

```scala
import com.raquo.laminar.api.L._
import io.laminext.fetch._
import scala.scalajs.js.typedarray.ArrayBuffer
import scala.concurrent.Future

val s: EventStream[FetchResponse[ArrayBuffer]] = Fetch.post("https://...").arrayBuffer
// OR
val f: Future[FetchResponse[ArrayBuffer]] = Fetch.post("https://...").future.arrayBuffer()
```

### Custom

You can provide a custom response reader: defined by a `Response => Future[A]` function.

```scala
import com.raquo.laminar.api.L._
import io.laminext.fetch._
import org.scalajs.dom.Response
import scala.concurrent.Future

val myReader: Response => Future[MyData] = ??? 

val s: EventStream[FetchResponse[MyData]] = Fetch.post("https://...").build(myReader)
// OR
val f: Future[FetchResponse[MyData]] = Fetch.post("https://...").future(myReader)
```

### Aborting future-based requests

When using one of the builders that return a `Future`, you can provide an optional `AbortController` as a parameter. 
This `AbortController` can then be used to abort the requests:

```scala
import com.raquo.laminar.api.L._
import io.laminext.fetch._
import org.scalajs.dom.AbortController
import scala.concurrent.Future

val abortController = new AbortController()

val f: Future[FetchResponse[String]] = Fetch.post("https://...").future.text(abortController)

abortController.abort()
```

### circe support

If you use [circe](https://circe.github.io/circe/), there's a `fetch-circe` module available. See [circe support](/fetch/circe).

