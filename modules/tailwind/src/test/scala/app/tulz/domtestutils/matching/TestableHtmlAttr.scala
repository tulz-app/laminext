package app.tulz.domtestutils.matching

import app.tulz.diff.StringDiff
import com.raquo.domtestutils.Utils.repr
import com.raquo.domtestutils.matching.ExpectedNode
import com.raquo.domtestutils.matching.MaybeError
import com.raquo.domtestutils.matching.Rule
import com.raquo.domtypes.generic.keys.HtmlAttr
import org.scalajs.dom

class TestableHtmlAttr[V](val attr: HtmlAttr[V]) extends AnyVal {

  def is(expectedValue: V): Rule = (testNode: ExpectedNode) => {
    testNode.addCheck(nodeAttrIs(Some(expectedValue)))
  }

  def isSorted(expectedValue: V): Rule = (testNode: ExpectedNode) => {
    testNode.addCheck(nodeAttrIs(Some(expectedValue), sorted = true))
  }

  def isEmpty: Rule = (testNode: ExpectedNode) => {
    testNode.addCheck(nodeAttrIs(None))
  }

  private def sortValue[U](value: U): U = {
    value match {
      case str: String =>
        str.split("\\s+").toSeq.sorted.mkString(" ").asInstanceOf[U]
      case value => value
    }

  }

  private[domtestutils] def nodeAttrIs(maybeExpectedValue: Option[V], sorted: Boolean = false)(node: dom.Node): MaybeError = {
    node match {
      case (element: dom.html.Element) =>
        val maybeActualValue = getAttr(element)
        (maybeActualValue, maybeExpectedValue) match {
          case (None, None) => None
          case (Some(actualValue), Some(expectedValue)) =>
            val actualValueMaybeSorted =
              if (sorted) {
                sortValue(actualValue)
              } else {
                actualValue
              }
            val expectedValueMaybeSorted =
              if (sorted) {
                sortValue(expectedValue)
              } else {
                expectedValue
              }
            if (actualValueMaybeSorted == expectedValueMaybeSorted) {
              None
            } else {
              val diff = StringDiff(actualValueMaybeSorted.toString, expectedValueMaybeSorted.toString)
              Some(s"Attr `${attr.name}` value is incorrect: actual value ${repr(actualValueMaybeSorted)}, expected value ${repr(expectedValueMaybeSorted)}\n. Diff:\n${diff}")
            }

          case (None, Some(expectedValue)) =>
            if (attr.codec.encode(expectedValue) == null) {
              None // Note: `encode` returning `null` is exactly how missing attribute values are defined, e.g. in BooleanAsAttrPresenceCodec
            } else {
              Some(s"Attr `${attr.name}` is missing, expected ${repr(expectedValue)}")
            }
          case (Some(actualValue), None) =>
            Some(s"Attr `${attr.name}` should not be present: actual value ${repr(actualValue)}, expected to be missing")
        }
      case _ =>
        Some(s"Unable to verify Attr `${attr.name}` because node $node is not a DOM HTML Element (might be a text node?)")
    }
  }

  private[domtestutils] def getAttr(element: dom.html.Element): Option[V] = {
    // Note: for boolean-as-presence attributes, this returns `None` instead of `Some(false)` when the attribute is missing.
    if (element.hasAttribute(attr.name)) {
      Some(attr.codec.decode(element.getAttribute(attr.name)))
    } else {
      None
    }
  }

}
