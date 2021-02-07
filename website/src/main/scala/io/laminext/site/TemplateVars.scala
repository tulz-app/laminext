package io.laminext.site

object TemplateVars {

  val vars = Seq(
    "laminextVersion" -> "0.12.0-M2"
  )

  def apply(s: String): String =
    vars.foldLeft(s) { case (acc, (varName, varValue)) =>
      acc.replace(s"{{${varName}}}", varValue)
    }

}
