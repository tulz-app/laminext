```scala
libraryDependencies += "io.laminext" %%% "fetch-circe" % "{{laminextVersion}}"
```

```scala
import io.laminext.fetch.circe._
// note – import this *instead* of io.laminext.fetch._ 
```

This module allows using the types that have a corresponding `Encoder` and/or `Decoder` as a request body and/or 
as a response type.

## Specifying a circe-encoded request body

```scala
import io.laminext.fetch.circe._  
import io.circe.generic.JsonCodec

@JsonCodec
case class MyRequest()

Fetch.post("https://...", body = MyRequest())
```

## Always decode the response

This will always try to decode the response body, regardless of the response status code.

```scala
import com.raquo.laminar.api.L._
import io.laminext.fetch.circe._
import io.circe.generic.JsonCodec

@JsonCodec
case class MyResponse()

val responseStream: EventStream[FetchResponse[MyResponse]] = Fetch.get("https://...").decode[MyResponse]
```

## Decode the "okay" response

This will only try to decode the response body if the returned status code is `2xx`.

Otherwise, the event stream will emit a `NonOkayResponse` error. 

```scala
import com.raquo.laminar.api.L._
import io.laminext.fetch.circe._
import io.circe.generic.JsonCodec

@JsonCodec
case class MyResponse()

val responseStream: EventStream[FetchResponse[MyResponse]] = Fetch.get("https://...").decodeOkay[MyResponse]
```

## Decode either

This will decode the okay (`2xx`) and non-okay responses into different types and "return" an `Either[ErrorResponse, OkayResponse]`.

```scala
import com.raquo.laminar.api.L._
import io.laminext.fetch.circe._
import io.circe.generic.JsonCodec

@JsonCodec
case class MyResponse()

@JsonCodec
case class MyError()

val responseStream: EventStream[FetchResponse[Either[MyError, MyResponse]]] =
  Fetch.get("https://...").decodeEither[MyError, MyResponse]
```

## Decoding errors

When the response can not be parsed/decoded, the event stream will emit
an `ResponseDecodeFailed` containing the underlying `io.circe.Error` as well as the original
response.  
