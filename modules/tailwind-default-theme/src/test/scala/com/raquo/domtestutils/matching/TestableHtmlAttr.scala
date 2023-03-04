package com.raquo.domtestutils.matching

import app.tulz.diff.TokenDiff
import com.raquo.domtestutils.Utils.repr
import org.scalajs.dom

class TestableHtmlAttr[V](
  val name: String,
  val encode: V => String,
  val decode: String => V
) {

  def is(expectedValue: V): Rule = (testNode: ExpectedNode) => {
    testNode.addCheck(nodeAttrIs(Some(expectedValue)))
  }

  def isSorted(expectedValue: V): Rule = (testNode: ExpectedNode) => {
    testNode.addCheck(nodeAttrIs(Some(expectedValue), sorted = true))
  }

  def isEmpty: Rule = (testNode: ExpectedNode) => {
    testNode.addCheck(nodeAttrIs(None))
  }

  private def sortValue[U](value: U): U =
    value match {
      case str: String =>
        str.split("\\s+").toSeq.sorted.mkString(" ").asInstanceOf[U]
      case value       => value
    }

  private[domtestutils] def nodeAttrIs(maybeExpectedValue: Option[V], sorted: Boolean = false)(node: dom.Node): MaybeError = {
    node match {

      case (element: dom.html.Element) =>
        val maybeActualValue = getAttr(element)
        (maybeActualValue, maybeExpectedValue) match {

          case (Some(actualValue), Some(expectedValue)) =>
            val actualValueMaybeSorted   =
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
              val (diffActual, diffExpected) =
                TokenDiff.ansiBoth(actualValueMaybeSorted.toString, expectedValueMaybeSorted.toString)

              Some(s"""|Attr `${name}` value is incorrect:
                       |- Actual:   ${repr(actualValue)}
                       |- Expected: ${repr(expectedValue)}
                       |- Actual diff:\n${diffActual}\n
                       |- Expected diff:\n${diffExpected}
                       |""".stripMargin)
            }

          case (None, Some(expectedValue)) =>
            if (encode(expectedValue) == null) {
              None // Note: `encode` returning `null` is exactly how missing attribute values are defined, e.g. in BooleanAsAttrPresenceCodec
            } else {
              Some(s"""|Attr `${name}` is missing:
                       |- Actual:   (no attribute)
                       |- Expected: ${repr(expectedValue)}
                       |""".stripMargin)
            }

          case (Some(actualValue), None) =>
            Some(s"""|Attr `${name}` should not be present:
                     |- Actual:   ${repr(actualValue)}
                     |- Expected: (no attribute)
                     |""".stripMargin)

          case (None, None) =>
            None
        }

      case _ =>
        Some(s"Unable to verify Attr `${name}` because node $node is not a DOM HTML Element (might be a text node?)")
    }
  }

  private[domtestutils] def getAttr(element: dom.html.Element): Option[V] = {
    // Note: for boolean-as-presence attributes, this returns `None` instead of `Some(false)` when the attribute is missing.
    if (element.hasAttribute(name)) {
      Some(decode(element.getAttribute(name)))
    } else {
      None
    }
  }
}
