package io.laminext.websocket

import org.scalajs.dom
import org.scalajs.dom.Blob

import scala.scalajs.js
import scala.scalajs.js.typedarray.ArrayBuffer
import scala.util.Try

final class WebSocketReceiveStringBuilder(url: String) {

  @inline def sendString: WebSocket[String, String] =
    new WebSocket(url, initialize.text, send.string, receive.string)
  @inline def sendText[Send](encode: Send => String): WebSocket[String, Send] =
    new WebSocket(url, initialize.text, send.text(encode), receive.string)
  @inline def sendBlob: WebSocket[String, Blob] =
    new WebSocket(url, initialize.blob, send.blob, receive.string)
  @inline def sendArray: WebSocket[String, ArrayBuffer] =
    new WebSocket(url, initialize.arraybuffer, send.arraybuffer, receive.string)

}

final class WebSocketReceiveTextBuilder[Receive](
  url: String,
  decode: String => Either[Throwable, Receive]
) {

  @inline def sendString: WebSocket[Receive, String] =
    new WebSocket(url, initialize.text, send.string, receive.text(decode))
  @inline def sendText[Send](encode: Send => String): WebSocket[Receive, Send] =
    new WebSocket(url, initialize.text, send.text(encode), receive.text(decode))
  @inline def sendBlob: WebSocket[Receive, Blob] =
    new WebSocket(url, initialize.blob, send.blob, receive.text(decode))
  @inline def sendArray: WebSocket[Receive, ArrayBuffer] =
    new WebSocket(url, initialize.arraybuffer, send.arraybuffer, receive.text(decode))

}

final class WebSocketReceiveBlobBuilder(url: String) {

  @inline def sendString: WebSocket[Blob, String] =
    new WebSocket(url, initialize.text, send.string, receive.blob)
  @inline def sendText[Send](encode: Send => String): WebSocket[Blob, Send] =
    new WebSocket(url, initialize.text, send.text(encode), receive.blob)
  @inline def sendBlob: WebSocket[Blob, Blob] =
    new WebSocket(url, initialize.blob, send.blob, receive.blob)
  @inline def sendArray: WebSocket[Blob, ArrayBuffer] =
    new WebSocket(url, initialize.arraybuffer, send.arraybuffer, receive.blob)

}

final class WebSocketReceiveArrayBufferBuilder(url: String) {

  @inline def sendString: WebSocket[ArrayBuffer, String] =
    new WebSocket(url, initialize.text, send.string, receive.arraybuffer)
  @inline def sendText[Send](encode: Send => String): WebSocket[ArrayBuffer, Send] =
    new WebSocket(url, initialize.text, send.text(encode), receive.arraybuffer)
  @inline def sendBlob: WebSocket[ArrayBuffer, Blob] =
    new WebSocket(url, initialize.blob, send.blob, receive.arraybuffer)
  @inline def sendArray: WebSocket[ArrayBuffer, ArrayBuffer] =
    new WebSocket(url, initialize.arraybuffer, send.arraybuffer, receive.arraybuffer)

}

final class WebSocketBuilder(url: String) {

  @inline def string: WebSocket[String, String] = new WebSocket(url, initialize.text, send.string, receive.string)

  @inline def text[Receive, Send](
    encode: Send => String,
    decode: String => Either[Throwable, Receive]
  ): WebSocket[Receive, Send] = new WebSocket(url, initialize.text, send.text(encode), receive.text(decode))

  @inline def blob: WebSocket[dom.Blob, dom.Blob] = new WebSocket(url, initialize.blob, send.blob, receive.blob)

  @inline def arraybuffer: WebSocket[js.typedarray.ArrayBuffer, js.typedarray.ArrayBuffer] =
    new WebSocket(url, initialize.arraybuffer, send.arraybuffer, receive.arraybuffer)

  @inline def receiveString: WebSocketReceiveStringBuilder =
    new WebSocketReceiveStringBuilder(url)
  @inline def receiveText[Receive](decode: String => Either[Throwable, Receive]): WebSocketReceiveTextBuilder[Receive] =
    new WebSocketReceiveTextBuilder[Receive](url, decode)
  @inline def receiveBlob: WebSocketReceiveBlobBuilder =
    new WebSocketReceiveBlobBuilder(url)
  @inline def receiveArray: WebSocketReceiveArrayBufferBuilder =
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
