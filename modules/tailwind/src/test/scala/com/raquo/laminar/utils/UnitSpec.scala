package com.raquo.laminar.utils

import com.raquo.laminar.api.L._
import app.tulz.laminar.ext.AmAny
import app.tulz.laminar.ext.AmendedHtmlTag
import com.raquo.domtestutils.Utils
import com.raquo.domtestutils.scalatest.MountSpec
import org.scalajs.dom
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class UnitSpec extends AnyFunSpec with Matchers with LaminarSpec with MountSpec with Utils {

  implicit class SyntaxTestAmendedTag[R <: dom.html.Element, AmType <: AmAny](tag: AmendedHtmlTag[R, AmType]) {

    def ====[T <: dom.html.Element, Am](expectedClass: String): Unit = {
      mount(tag())

      expectNode(
        tag.like(cls.is(expectedClass.trim.replaceAll("\\s+", " ")))
      )

      unmount()
    }

  }

}
