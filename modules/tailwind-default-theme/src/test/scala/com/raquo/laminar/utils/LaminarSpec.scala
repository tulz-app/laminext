package com.raquo.laminar.utils

import com.raquo.domtestutils.matching._
import com.raquo.domtestutils.EventSimulator
import com.raquo.domtestutils.MountOps
import com.raquo.laminar.api.L.CompositeSvgAttr
import com.raquo.laminar.api._
import com.raquo.laminar.codecs.StringAsIsCodec
import com.raquo.laminar.defs.complex.ComplexHtmlKeys.CompositeHtmlAttr
import com.raquo.laminar.defs.complex.ComplexHtmlKeys.CompositeHtmlProp
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.keys.HtmlProp
import com.raquo.laminar.keys.StyleProp
import com.raquo.laminar.keys.SvgAttr
import com.raquo.laminar.nodes.CommentNode
import com.raquo.laminar.nodes.ReactiveElement
import com.raquo.laminar.nodes.RootNode
import com.raquo.laminar.tags.Tag
import org.scalactic

trait LaminarSpec extends MountOps with RuleImplicits[Tag.Base, CommentNode, HtmlProp, HtmlAttr, SvgAttr, StyleProp] with EventSimulator {
  // === On nullable variables ===
  // `root` is nullable because if it was an Option it would be too easy to
  // forget to handle the `None` case when mapping or foreach-ing over it.
  // In test code, We'd rather have a null pointer exception than an assertion that you don't
  // realize isn't running because it's inside a None.foreach.
  var root: RootNode = null

  def sentinel: ExpectedNode = ExpectedNode.comment

  def mount(
    node: ReactiveElement.Base,
    clue: String = defaultMountedElementClue
  )(implicit
    pos: scalactic.source.Position
  ): Unit = {
    mountedElementClue = clue
    assertEmptyContainer("laminar.mount")
    root = L.render(containerNode, node)
  }

  def mount(
    clue: String,
    node: ReactiveElement.Base
  )(implicit
    pos: scalactic.source.Position
  ): Unit = {
    mount(node, clue)(pos)
  }

  override def unmount(clue: String = "unmount")(implicit pos: scalactic.source.Position): Unit = {
    assertRootNodeMounted("unmount:" + clue)
    doAssert(
      root != null,
      s"ASSERT FAILED [unmount:$clue]: Laminar root not found. Did you use Laminar's mount() method in LaminarSpec? Note: unfortunately this could conceal the true error message."
    )
    doAssert(
      root.child.ref == rootNode,
      s"ASSERT FAILED [unmount:$clue]: Laminar root's ref does not match rootNode. What did you do!?"
    )
    doAssert(
      root.unmount(),
      s"ASSERT FAILED [unmount:$clue]: Laminar root failed to unmount"
    )
    root = null
    // containerNode = null
    mountedElementClue = defaultMountedElementClue
  }

  implicit override def makeTagTestable(tag: Tag.Base): ExpectedNode = {
    ExpectedNode.element(tag.name)
  }

  implicit override def makeCommentBuilderTestable(commentBuilder: () => CommentNode): ExpectedNode = {
    ExpectedNode.comment
  }

  implicit override def makeAttrTestable[V](attr: HtmlAttr[V]): TestableHtmlAttr[V] = {
    new TestableHtmlAttr[V](attr.name, attr.codec.encode, attr.codec.decode)
  }

  implicit override def makePropTestable[V, DomV](prop: HtmlProp[V, DomV]): TestableProp[V, DomV] = {
    new TestableProp[V, DomV](prop.name, prop.codec.decode)
  }

  implicit override def makeStyleTestable[V](style: StyleProp[V]): TestableStyleProp[V] = {
    new TestableStyleProp[V](style.name)
  }

  implicit override def makeSvgAttrTestable[V](svgAttr: SvgAttr[V]): TestableSvgAttr[V] = {
    new TestableSvgAttr[V](svgAttr.name, svgAttr.codec.encode, svgAttr.codec.decode, svgAttr.namespaceUri)
  }

  implicit def makeCompositePropTestable(prop: CompositeHtmlProp): TestableProp[String, String] = {
    new TestableProp(prop.name, StringAsIsCodec.decode)
  }

  implicit def makeCompositeHtmlAttrTestable(attr: CompositeHtmlAttr): TestableHtmlAttr[String] = {
    new TestableHtmlAttr(attr.name, StringAsIsCodec.encode, StringAsIsCodec.decode)
  }

  implicit def makeCompositeSvgAttrTestable(attr: CompositeSvgAttr): TestableSvgAttr[String] = {
    new TestableSvgAttr(attr.name, StringAsIsCodec.encode, StringAsIsCodec.decode, namespace = None)
  }
}
