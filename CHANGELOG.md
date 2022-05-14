# Changelog

### WIP

* Methods in `FutureOps` now require an implicit `ExecutionContext` (before, the global EC was used)
* `Fetch` builders now require an implicit `ExecutionContext` (before, the global EC was used) 

### 0.14.3

* Bugfix: in the WebSocket client, a check for the "connected" status was incomplete - https://github.com/tulz-app/laminext/pull/108 (@lavrov) 

### 0.14.2

Update Laminar to 0.14.2

### 0.14.1

Skipped

### 0.14.0

Update to Laminar `v0.14.0` and `scala-js-dom` `v2.0.0`

* input element ops – `files` returns a `Signal[FileList]` instead of `Signal[Seq[File]]` (use `.toSeq` to get a `Seq`)
* API new: task queue

### 0.13.10

* API: new: `input`/`textArea` `.changes`/`.value`/`.checked`/`.files` now have additional overloaded versions 
  that accept a `changeStreamTransform: EventStream[Event] => EventStream[Event]` parameter (can be used, for example, to
  debounce the "changed" events)
* API: new: `input`/`textArea` `.validated`/`.validatedCheckBox`/`.validatedFile`/`.validatedFiles` now accept a second
  (optional) parameter – `changeStreamTransform: EventStream[Event] => EventStream[Event]` – which is passed to the 
  `.value`/`.checked`/`.files`
* API: additional validation builders accepting a functions like `check: A => Option[Err]` that can construct error messages
  using the value that is being validated

### 0.13.9

* update to `Laminar` v0.13.1
* API: new: `ValidatedElement` now exposes the `.resetError` observer

### 0.13.8

* API: new: `mutationObserver`
* Fix: stored vars now check if local storage is accessible
* API: observable extension methods are now source extension methods
* API: now accepting source and sink where observable and observer where expected before 

### 0.13.7

* API: new: `.addSwitchingObserver` and `.addOptionalSwitchingObserver`

### 0.13.6

* Bugfix: websocket client was failing to connect when no sub-protocol was specified (defaulting to "", which 
  was incorrect)  
* Minor changes to improve error handling in the `fetch` module
  * `ResponseDecodeFailed` is gone, `ResponseError` is used instead
  
### 0.13.5

##### `fetch` module: `RequestUrl`

* `hostname` renamed into `host` to reflect that it can contain the port
* `path` renamed into `segments` to better reflect what it is
* `uri` function renamed to `url`
* `RequestUrl` now makes sure to not contain empty segments (thus it is no longer possible,
  accidentally or otherwise, to have a double `/` in the request URL)
* new `RequestUrl.fromLocation(location: dom.raw.Location)` builder
* Breaking: `addPath(path: String*)` renamed to `addSegments(path: String*)`
* New: `withParams(params: Map[String, Seq[String]])`
* New: `withSegments(segments: String*)`

`*path` methods will split the argument by `/` into a `Seq[String]`, ensuring there are 
no empty segments.

`url`, `RequestUrl.apply` and `RequestUrl.fromLocation` ensure there are no empty segments as well.


##### `util` module: new utilities in the

* `UrlString` object
  * `parse` – parse a string into a `dom.raw.Location`
  ```scala
  val myUrl = "https://laminext.dev/path?param=1"
  val location: dom.raw.Location = UrlString.parse(myUrl) 
  console.print(location.origin)
  ```
* `UrlUtils` object
  * `encodeSearchParams(params: Map[String, Seq[String]]): String` – encode the given parameters into a URL search string (`?param=1&...`)
  * `decodeSearchParams(search: String): Map[String, Seq[String]]` – decode a URL search string into a map of parameters

### 0.13.4

Sourcemaps are now pointing to GitHub.

### 0.13.3

Update to circe 0.14.1.

### 0.13.2

* new: `protocol` parameter for WebSocket (thanks @ghostdogpr!) 

### 0.13.1

Laminar 0.13.0, Scala 3.0.0 

### 0.13.0

Laminar 0.13.0, Scala 3-RC3

### 0.12.3

* New: `websocket-zio-json` [module](https://laminext.dev/websocket/zio-json). Thanks [Kit Langton](https://github.com/kitlangton) for the [contribution](https://github.com/tulz-app/laminext/pull/17)
* Updated to Laminar v0.12.2

### 0.12.2

* Bugfix: in `SignalOfBooleanOps`, the `&&` operator was actually doing `||` (thanks [@ngochai94](https://github.com/ngochai94) for the [PR](https://github.com/tulz-app/laminext/pull/14)!)

### 0.12.0

First usable release.
