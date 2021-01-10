package io.laminext.websocket

import internal._
import org.scalajs.dom
import org.scalajs.dom.Blob

import scala.concurrent.duration.DurationInt
import scala.concurrent.duration.FiniteDuration
import scala.scalajs.js
import scala.scalajs.js.typedarray.ArrayBuffer
import scala.util.Try

final class WebSocketBuilder[Receive, Send](
  url: String,
  initializer: WebSocketInitialize,
  sender: WebSocketSend[Send],
  receiver: WebSocketReceive[Receive],
) {

  @inline def build(
    managed: Boolean = true,
    bufferWhenDisconnected: Boolean = true,
    bufferSize: Int = 50,
    autoReconnect: Boolean = true,
    reconnectDelay: FiniteDuration = 1.second,
    reconnectDelayOffline: FiniteDuration = 20.second,
    reconnectRetries: Int = 10
  ): WebSocket[Receive, Send] = new WebSocket[Receive, Send](
    url = url,
    initializer = initializer,
    sender = sender,
    receiver = receiver,
    managed = managed,
    bufferWhenDisconnected = bufferWhenDisconnected,
    bufferSize = bufferSize,
    autoReconnect = autoReconnect,
    reconnectDelay = reconnectDelay,
    reconnectDelayOffline = reconnectDelayOffline,
    reconnectRetries = reconnectRetries
  )

}

final class WebSocketReceiveStringBuilder(url: String) {

  @inline def sendString: WebSocketBuilder[String, String] =
    new WebSocketBuilder(url, initialize.text, send.string, receive.string)
  @inline def sendText[Send](encode: Send => String): WebSocketBuilder[String, Send] =
    new WebSocketBuilder(url, initialize.text, send.text(encode), receive.string)
  @inline def sendBlob: WebSocketBuilder[String, Blob] =
    new WebSocketBuilder(url, initialize.blob, send.blob, receive.string)
  @inline def sendArrayBuffer: WebSocketBuilder[String, ArrayBuffer] =
    new WebSocketBuilder(url, initialize.arraybuffer, send.arraybuffer, receive.string)

}

final class WebSocketReceiveTextBuilder[Receive](
  url: String,
  decode: String => Either[Throwable, Receive]
) {

  @inline def sendString: WebSocketBuilder[Receive, String] =
    new WebSocketBuilder(url, initialize.text, send.string, receive.text(decode))
  @inline def sendText[Send](encode: Send => String): WebSocketBuilder[Receive, Send] =
    new WebSocketBuilder(url, initialize.text, send.text(encode), receive.text(decode))
  @inline def sendBlob: WebSocketBuilder[Receive, Blob] =
    new WebSocketBuilder(url, initialize.blob, send.blob, receive.text(decode))
  @inline def sendArrayBuffer: WebSocketBuilder[Receive, ArrayBuffer] =
    new WebSocketBuilder(url, initialize.arraybuffer, send.arraybuffer, receive.text(decode))

}

final class WebSocketReceiveBlobBuilder(url: String) {

  @inline def sendString: WebSocketBuilder[Blob, String] =
    new WebSocketBuilder(url, initialize.text, send.string, receive.blob)
  @inline def sendText[Send](encode: Send => String): WebSocketBuilder[Blob, Send] =
    new WebSocketBuilder(url, initialize.text, send.text(encode), receive.blob)
  @inline def sendBlob: WebSocketBuilder[Blob, Blob] =
    new WebSocketBuilder(url, initialize.blob, send.blob, receive.blob)
  @inline def sendArrayBuffer: WebSocketBuilder[Blob, ArrayBuffer] =
    new WebSocketBuilder(url, initialize.arraybuffer, send.arraybuffer, receive.blob)

}

final class WebSocketReceiveArrayBufferBuilder(url: String) {

  @inline def sendString: WebSocketBuilder[ArrayBuffer, String] =
    new WebSocketBuilder(url, initialize.text, send.string, receive.arraybuffer)
  @inline def sendText[Send](encode: Send => String): WebSocketBuilder[ArrayBuffer, Send] =
    new WebSocketBuilder(url, initialize.text, send.text(encode), receive.arraybuffer)
  @inline def sendBlob: WebSocketBuilder[ArrayBuffer, Blob] =
    new WebSocketBuilder(url, initialize.blob, send.blob, receive.arraybuffer)
  @inline def sendArrayBuffer: WebSocketBuilder[ArrayBuffer, ArrayBuffer] =
    new WebSocketBuilder(url, initialize.arraybuffer, send.arraybuffer, receive.arraybuffer)

}

final class WebSocketReceiveBuilder(private[websocket] val url: String) {

  @inline def string: WebSocketBuilder[String, String] = new WebSocketBuilder(url, initialize.text, send.string, receive.string)

  @inline def text[Receive, Send](
    encode: Send => String,
    decode: String => Either[Throwable, Receive]
  ): WebSocketBuilder[Receive, Send] = new WebSocketBuilder(url, initialize.text, send.text(encode), receive.text(decode))

  @inline def blob: WebSocketBuilder[dom.Blob, dom.Blob] = new WebSocketBuilder(url, initialize.blob, send.blob, receive.blob)

  @inline def arraybuffer: WebSocketBuilder[js.typedarray.ArrayBuffer, js.typedarray.ArrayBuffer] =
    new WebSocketBuilder(url, initialize.arraybuffer, send.arraybuffer, receive.arraybuffer)

  @inline def receiveString: WebSocketReceiveStringBuilder =
    new WebSocketReceiveStringBuilder(url)
  @inline def receiveText[Receive](decode: String => Either[Throwable, Receive]): WebSocketReceiveTextBuilder[Receive] =
    new WebSocketReceiveTextBuilder[Receive](url, decode)
  @inline def receiveBlob: WebSocketReceiveBlobBuilder =
    new WebSocketReceiveBlobBuilder(url)
  @inline def receiveArrayBuffer: WebSocketReceiveArrayBufferBuilder =
    new WebSocketReceiveArrayBufferBuilder(url)

}

private[websocket] object initialize {
  def text: WebSocketInitialize        = _ => {}
  def blob: WebSocketInitialize        = _.binaryType = "blob"
  def arraybuffer: WebSocketInitialize = _.binaryType = "arraybuffer"
}

private[websocket] object send {
  def string: WebSocketSend[String] =
    text(identity)
  def text[Send](encode: Send => String): WebSocketSend[Send] =
    (socket, data) => socket.send(encode(data))
  def blob: WebSocketSend[dom.Blob] =
    (socket, data) => socket.send(data)
  def arraybuffer: WebSocketSend[js.typedarray.ArrayBuffer] =
    (socket, data) => socket.send(data)
}

private[websocket] object receive {
  def string: WebSocketReceive[String] =
    text(s => Right(s))
  def text[Receive](decode: String => Either[Throwable, Receive]): WebSocketReceive[Receive] =
    message => decode(message.data.toString)
  def blob: WebSocketReceive[dom.Blob] =
    message => Try(message.data.asInstanceOf[dom.Blob]).toEither
  def arraybuffer: WebSocketReceive[js.typedarray.ArrayBuffer] =
    message => Try(message.data.asInstanceOf[js.typedarray.ArrayBuffer]).toEither
}
