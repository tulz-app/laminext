package io.laminext.syntax

import io.laminext.core.FutureChildReceiver
import io.laminext.core.FutureChildrenReceiver

trait ReceiversSyntax {

  val futureChild: FutureChildReceiver.type = FutureChildReceiver

  val futureChildren: FutureChildrenReceiver.type = FutureChildrenReceiver

}
