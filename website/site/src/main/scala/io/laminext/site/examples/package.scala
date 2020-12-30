package io.laminext.site

import com.raquo.laminar.api.L._

package object examples {

  type TitledExample[E <: Element] = (String, sourcecode.Text[E])

}
