package io.laminext.site

import io.laminext.tailwind.theme.TailwindModal
import io.laminext.ui._

object Theme {

  val modalStyling: Modal.Styling = TailwindModal.styling

  val mobileMenuModalStyling: Modal.Styling = TailwindModal.styling.customize(
    contentWrapTransition = _.customize(
      nonHidden = _ :+ "bg-gray-900"
    )
  )

}
