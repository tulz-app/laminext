package io.laminext.syntax

import io.laminext.FutureChildReceiver
import io.laminext.FutureChildrenReceiver

trait ReceiversSyntax {

  val futureChild: FutureChildReceiver.type = FutureChildReceiver

  val futureChildren: FutureChildrenReceiver.type = FutureChildrenReceiver

}
