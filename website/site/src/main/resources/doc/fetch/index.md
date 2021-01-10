```scala
libraryDependencies += "io.laminext" %%% "fetch" % "{{laminextVersion}}"
```

```scala
import io.laminext.fetch._
```

An HTTP client library for Airstream built on top of the [Fetch API](https://developer.mozilla.org/en-US/docs/Web/API/Fetch_API).

In a nutshell, it works like this: you describe a request and get back an `EventStream` that will emit the HTTP response when it's ready (or an error if the 
underlying `fetch` call fails). This event stream will send the request when it (the stream) starts, and abort the request (if it's still pending) when the stream stops.

To build a request, start with specifying the [request method](/fetch/request-method).

You can specify all the settings that are supported by the underlying Fetch API, including the [request headers](/fetch/request-headers),
the [request body](/fetch/request-body) and [other request settings](/fetch/request-settings).

After you've defined the request, you specify how you want to process the [response](/fetch/response) and that's it.
