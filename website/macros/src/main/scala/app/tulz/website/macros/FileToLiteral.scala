package app.tulz.website.macros

import java.io.File
import scala.reflect.macros.blackbox

object FileToLiteral {

  def apply(fileName: String): String = macro FileToLiteralImpl.referEmbeddedFile

}

class FileToLiteralImpl(val c: blackbox.Context) {
  import c._
  import universe._

  def referEmbeddedFile(fileName: c.Expr[String]) = fileName.tree match {
    case Literal(Constant(fileNameStr: String)) =>
      val file = {
        if (fileNameStr.startsWith("/")) {
          new File(fileNameStr)
        } else {
          new File(new File(c.enclosingPosition.source.path).getParentFile.getAbsolutePath.split('/').dropWhile(_ != "scala").drop(1).mkString("/"), fileNameStr)
        }
      }.toPath

      val packageName: String = file.getParent.toString.replace("/", ".").dropWhile(_ == '.')

      val className: String =
        s"EmbeddedFile_${file.getFileName.toString.replaceAll("\\W", "_").replaceAll("_+", "_")}"

      val field = "content"

      val maybeSelectObject = packageName
        .split('.').foldLeft[Option[c.Tree]](
          None
        ) { (chain, next) =>
          chain match {
            case None        => Some(Ident(TermName(next)))
            case Some(chain) => Some(Select(chain, TermName(next)))
          }
        }

      maybeSelectObject
        .map { selectObject =>
          Select(Select(selectObject, TermName(className)), TermName(field))
        }.getOrElse(throw new RuntimeException(s"invalid package and class: ${packageName} ${className} for file ${fileNameStr}"))
  }

}
