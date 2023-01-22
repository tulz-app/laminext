package io.laminext.ui.dnd

import org.scalajs.dom.DragEvent
import org.scalajs.dom.FileList

import scala.util.Try

trait DragDropEncoder[T] {
  self =>

  def format: String

  def setData(ev: DragEvent, value: T): Unit

  def contramap[U](f: U => T): DragDropEncoder[U] = new DragDropEncoder[U] {

    def format: String = self.format

    def setData(ev: DragEvent, value: U): Unit = self.setData(ev, f(value))

  }

  def withFormat(fmt: String): DragDropEncoder[T] = new DragDropEncoder[T] {

    def format: String = fmt

    def setData(ev: DragEvent, value: T): Unit = self.setData(ev, value)

  }

}

trait DragDropDecoder[T] {
  self =>

  def format: String

  def willAccept(ev: DragEvent): Boolean = Option(ev.dataTransfer).exists(_.types.contains(format))

  def getData(ev: DragEvent): Either[String, T]

  def map[U](f: T => U): DragDropDecoder[U] = new DragDropDecoder[U] {

    def format: String = self.format

    def getData(ev: DragEvent): Either[String, U] = self.getData(ev).map(f)

  }

  def withFormat(fmt: String): DragDropDecoder[T] = new DragDropDecoder[T] {

    def format: String = fmt

    def getData(ev: DragEvent): Either[String, T] = self.getData(ev)

  }

}

trait DragDropTextEncoder[T] extends DragDropEncoder[T] {

  def encode(value: T): String

  def setData(ev: DragEvent, value: T): Unit =
    ev.dataTransfer.setData(format, encode(value))

}

trait DragDropTextDecoder[T] extends DragDropDecoder[T] {

  def decode(string: String): Either[String, T]

  def getData(ev: DragEvent): Either[String, T] =
    Try(
      Option(ev.dataTransfer).flatMap(dt => Option(dt.getData(format)))
    ).toOption.flatten
      .filterNot(_.isEmpty).map { string =>
        decode(string)
      }.getOrElse(Left("no data"))

}

object DragDropFormats {

  val `text/plain`: String     = "text/plain"
  val `x-text/string`: String  = "x-text/string"
  val `x-text/int`: String     = "x-text/int"
  val `x-text/long`: String    = "x-text/long"
  val `x-text/boolean`: String = "x-text/boolean"
  val `x-text/double`: String  = "x-text/double"
  val `x-text/float`: String   = "x-text/float"
  val `Files`: String          = "Files"

}

object DragDropDecoder {

  import DragDropFormats._

  def apply[T: DragDropDecoder]: DragDropDecoder[T] = implicitly

  def plain[T](
    dec: String => Either[String, T]
  ): DragDropDecoder[T] = instance[T](`text/plain`, dec)

  def instance[T](
    fmt: String,
    dec: String => Either[String, T]
  ): DragDropDecoder[T] = new DragDropTextDecoder[T] {
    def format: String = fmt

    def decode(string: String): Either[String, T] = dec(string)
  }

  implicit val stringDecoder: DragDropDecoder[String]   = instance(`x-text/string`, s => Right(s))
  implicit val intDecoder: DragDropDecoder[Int]         = instance(`x-text/int`, s => Try(s.toInt).toEither.left.map(_.getMessage))
  implicit val longDecoder: DragDropDecoder[Long]       = instance(`x-text/long`, s => Try(s.toLong).toEither.left.map(_.getMessage))
  implicit val doubleDecoder: DragDropDecoder[Double]   = instance(`x-text/boolean`, s => Try(s.toDouble).toEither.left.map(_.getMessage))
  implicit val floatDecoder: DragDropDecoder[Float]     = instance(`x-text/double`, s => Try(s.toFloat).toEither.left.map(_.getMessage))
  implicit val booleanDecoder: DragDropDecoder[Boolean] = instance(`x-text/float`, s => Try(s.toBoolean).toEither.left.map(_.getMessage))

  implicit val filesDecoder: DragDropDecoder[FileList] = new DragDropDecoder[FileList] {

    val format: String = DragDropFormats.`Files`

    def getData(ev: DragEvent): Either[String, FileList] =
      Option(ev.dataTransfer.files).toRight("no files")

  }

}

object DragDropEncoder {

  import DragDropFormats._

  def apply[T: DragDropEncoder]: DragDropEncoder[T] = implicitly

  def plain[T](
    enc: T => String,
  ): DragDropEncoder[T] = instance[T](`text/plain`, enc)

  def instance[T](
    fmt: String,
    enc: T => String,
  ): DragDropEncoder[T] = new DragDropTextEncoder[T] {
    def format: String = fmt

    def encode(value: T): String = enc(value)
  }

  implicit val stringEncoder: DragDropEncoder[String]   = instance(`x-text/string`, identity)
  implicit val intEncoder: DragDropEncoder[Int]         = instance(`x-text/int`, _.toString)
  implicit val longEncoder: DragDropEncoder[Long]       = instance(`x-text/long`, _.toString)
  implicit val doubleEncoder: DragDropEncoder[Double]   = instance(`x-text/boolean`, _.toString)
  implicit val floatEncoder: DragDropEncoder[Float]     = instance(`x-text/float`, _.toString)
  implicit val booleanEncoder: DragDropEncoder[Boolean] = instance(`x-text/double`, _.toString)

}
