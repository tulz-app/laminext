package io.laminext.ui

import com.raquo.laminar.api.L._

object FileInputConfig {

  val empty: FileInputConfig = _ => emptyMod

  val default: FileInputConfig = classes {
    case FileInputElement.Status.Selecting => "lx-file-input-selecting"
    case FileInputElement.Status.Ready     => "lx-file-input-ready"
    case FileInputElement.Status.Invalid   => "lx-file-input-invalid"
  }

  @inline def classes(styling: FileInputElement.Status => String): FileInputConfig =
    $status => cls <-- $status.map(styling)

}
