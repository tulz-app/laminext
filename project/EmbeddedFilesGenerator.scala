import sbt._
import sbt.Keys._
import sbt.KeyRanks._
import java.nio.file.Files
import java.nio.file.Path
import sbt.nio._
import sbt.nio.Keys._

object EmbeddedFilesGenerator extends AutoPlugin {

  override def trigger = noTrigger

  object autoImport {
    val embedFiles            = taskKey[Seq[File]]("Creates an ExternalFile object with a content field for each file")
    val embedFilesGlob        = settingKey[String]("glob pattern")
    val embedFilesDirectories = settingKey[Seq[File]]("Directories to look for files")
  }

  import autoImport._

  override lazy val projectSettings: Seq[Setting[_]] = Seq(
    embedFilesDirectories := Seq((Compile / resourceDirectory).value),
    embedFilesGlob := "**/*.txt",
    embedFiles / fileInputs ++= embedFilesDirectories.value.map(_.toGlob / embedFilesGlob.value),
    embedFiles := {
      val outputDir = (Compile / sourceManaged).value.toPath / "scala"

      def packageName(path: Path): String = {
        path.getParent.toString.replaceAllLiterally("/", ".")
      }

      def className(path: Path): String = {
        s"EmbeddedFile_${path.getFileName.toString.replaceAll("\\W", "_").replaceAll("_+", "_")}"
      }

      val logger = streams.value.log
      def outputPath(path: Path): Path = {
        outputDir / path.getParent.toString / s"${className(path)}.scala"
      }

      val sourceMap =
        embedFiles.inputFiles.view.flatMap { path =>
          embedFilesDirectories.value.flatMap(path.toFile.relativeTo).headOption.map(_.toPath).map { relative =>
            outputPath(relative) -> path
          }
        }.toMap

      val existingTargets = fileTreeView.value
        .list(outputDir.toGlob / **).flatMap { case (p, _) =>
          if (p.toFile().isFile() && !sourceMap.contains(p)) {
            Files.deleteIfExists(p)
            None
          } else {
            Some(p)
          }
        }.toSet

      val changes      = embedFiles.inputFileChanges
      val updatedPaths = (changes.created ++ changes.modified).toSet
      val needCompile  = updatedPaths ++ sourceMap.filterKeys(!existingTargets(_)).values
      needCompile.foreach { path =>
        embedFilesDirectories.value.flatMap(path.toFile.relativeTo).headOption.map(_.toPath).foreach { relative =>
          buildEmbeddedFile(
            input = path,
            output = outputPath(relative),
            packageName(relative),
            className(relative)
          )
        }
      }
      sourceMap.keys.toVector.map(_.toFile)
    }
  )

  def buildEmbeddedFile(input: Path, output: Path, packageName: String, className: String): Unit = {
    IO.write(
      output.toFile,
      s"""
         |package $packageName
         |
         |object $className {
         |  lazy val content: String = \"\"\"${IO.read(input.toFile).replaceAllLiterally("\"\"\"", "\\\"\\\"\\\"")}\"\"\"
         |}
         |""".stripMargin
    )
  }

}
