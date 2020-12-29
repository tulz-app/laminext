package app.tulz.website.macros

import java.io.File
import scala.io.Source
import scala.language.experimental.macros
import scala.reflect.macros.blackbox

object FileToLiteral {

  def apply(fileName: String): String = macro FileToLiteralImpl.readFile

}

class FileToLiteralImpl(val c: blackbox.Context) {
  import c._
  import universe._

  def readFile(fileName: c.Expr[String]): c.Expr[String] = fileName.tree match {
    case Literal(Constant(s: String)) =>
      val file = {
        if (s.startsWith("/")) {
          new File("website/src/main", s)
        } else {
          new File(new File(c.enclosingPosition.source.path).getParentFile, s)
        }
      }
      val source = Source.fromFile(file, "UTF-8")
      val res    = source.mkString
      source.close()
      c.Expr[String](Literal(Constant(res)))
  }

}
