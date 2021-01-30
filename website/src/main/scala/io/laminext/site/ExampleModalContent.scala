package io.laminext.site

import com.raquo.laminar.api.L._
import io.laminext.syntax.tailwind
import io.laminext.syntax.tailwind.ModalContent

object ExampleModalContent {

  val modalContent: Var[Option[tailwind.ModalContent]] = Var[Option[ModalContent]](Option.empty)

}
