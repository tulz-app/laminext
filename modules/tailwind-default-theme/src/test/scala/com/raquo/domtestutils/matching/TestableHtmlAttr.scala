package com.raquo.domtestutils.matching

import com.raquo.domtestutils.Utils.repr
import com.raquo.domtypes.generic.codecs.Codec
import org.scalajs.dom
import app.tulz.diff.TokenDiff

import scala.scalajs.js

// @TODO Create EventPropOps

class TestableProp[V, DomV](
  val name: String,
  val decode: DomV => V
) {

  def is(expectedValue: V): Rule = (testNode: ExpectedNode) => {
    testNode.addCheck(nodePropIs(maybeExpectedValue = Some(expectedValue)))
  }

  def isSorted(expectedValue: V): Rule = (testNode: ExpectedNode) => {
    testNode.addCheck(nodePropIs(Some(expectedValue), sorted = true))
  }

  def isEmpty: Rule = (testNode: ExpectedNode) => {
    testNode.addCheck(nodePropIs(maybeExpectedValue = None))
  }

  private def sortValue[U](value: U): U = {
    value match {
      case str: String =>
        str.split("\\s+").toSeq.sorted.mkString(" ").asInstanceOf[U]
      case value       => value
    }

  }
  private[domtestutils] def nodePropIs(maybeExpectedValue: Option[V], sorted: Boolean = false)(node: dom.Node): MaybeError = {
    val maybeActualValue = getProp(node)
    if (node.isInstanceOf[dom.html.Element]) {
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

            Some(s"""|Prop `${name}` value is incorrect:
                     |- Actual:   ${repr(actualValue)}
                     |- Expected: ${repr(expectedValue)}
                     |- Actual diff:\n${diffActual}\n
                     |- Expected diff:\n${diffExpected}
                     |""".stripMargin)
          }

        case (None, Some(expectedValue)) =>
          val rawActualValue = node.asInstanceOf[js.Dynamic].selectDynamic(name)
          Some(s"""|Prop `${name}` is empty or missing:
                   |- Actual (raw): ${repr(rawActualValue)}
                   |- Expected:     ${repr(expectedValue)}
                   |""".stripMargin)

        case (Some(actualValue), None) =>
          Some(s"""|Prop `${name}` should be empty or not present:
                   |- Actual:   ${repr(actualValue)}
                   |- Expected: (empty / not present)
                   |""".stripMargin)

        case (None, None) =>
          None
      }
    } else {
      Some(s"Unable to verify Prop `${name}` because node $node is not a DOM HTML Element (might be a text node?)")
    }
  }

  private[domtestutils] def getProp(node: dom.Node): Option[V] = {
    val propValue = node.asInstanceOf[js.Dynamic].selectDynamic(name)
    val jsUndef   = js.undefined

    propValue.asInstanceOf[Any] match {
      case str: String if str.isEmpty => None
      case `jsUndef`                  => None
      case null                       => None
      case _                          => Some(decode(propValue.asInstanceOf[DomV]))
    }
  }
}
