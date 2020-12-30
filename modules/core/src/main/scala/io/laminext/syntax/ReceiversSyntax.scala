package io.laminext.syntax

import io.laminext.FutureChildReceiver
import io.laminext.FutureChildrenReceiver
import io.laminext.UnsafeInnerHtmlReceiver

trait ReceiversSyntax {

  val futureChild: FutureChildReceiver.type = FutureChildReceiver

  val futureChildren: FutureChildrenReceiver.type = FutureChildrenReceiver

  val unsafeInnerHtml: UnsafeInnerHtmlReceiver.type = UnsafeInnerHtmlReceiver

}
