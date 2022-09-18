package io.laminext.site

import io.laminext.tailwind.theme.TailwindModal
import io.laminext.ui.theme.ModalConfig

object SiteTheme {

  val modalStyling: ModalConfig = TailwindModal.styling

  val mobileMenuModalStyling: ModalConfig = TailwindModal.styling.customize(
    contentWrapTransition = _.customize(
      nonHidden = _ :+ "bg-gray-900"
    )
  )

}
