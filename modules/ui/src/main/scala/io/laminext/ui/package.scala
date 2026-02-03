package io.laminext

import com.raquo.laminar.api.L._

package object ui {

  type FileInputConfig = Signal[FileInputElement.Status] => Mod[HtmlElement]

}
