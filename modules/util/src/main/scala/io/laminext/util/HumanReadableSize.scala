package io.laminext.util

import scala.util.Try

object HumanReadableSize {

  def format(fileSize: String): String = {
    Try(fileSize.toDouble)
      .map(Math.round(_).toDouble).fold(
        _ => s"$fileSize B",
        HumanReadableSize.format
      )
  }

  def format(fileSize: Double): String = {
    if (fileSize <= 0) return "0 B"
    // kilo, Mega, Giga, Tera, Peta, Exa, Zetta, Yotta
    val units: Array[String] = Array("B", "kB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB")
    val digitGroup: Int      = (Math.log10(fileSize) / Math.log10(1024)).toInt
    f"${fileSize / Math.pow(1024.0, digitGroup.toDouble)}%.0f ${units(digitGroup)}"
  }

}
