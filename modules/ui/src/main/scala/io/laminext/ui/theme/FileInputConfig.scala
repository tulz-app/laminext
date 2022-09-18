package io.laminext.ui.theme

import com.raquo.laminar.api.L._
import io.laminext.ui.FileInputElement

object FileInputConfig {

  val empty: FileInputConfig = _ => emptyMod

  @inline def classes(styling: FileInputElement.Status => String): FileInputConfig =
    $status => cls <-- $status.map(styling)

}
