package io.laminext.ui

import com.raquo.laminar.api.L._

package object theme {

  type FileInputConfig = Signal[FileInputElement.Status] => Mod[HtmlElement]

}
