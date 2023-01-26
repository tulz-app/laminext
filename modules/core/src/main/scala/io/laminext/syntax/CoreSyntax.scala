package io.laminext.syntax

import org.scalajs.dom

trait CoreSyntax
    extends SourceSyntax
    with ObservableOfBooleanSyntax
    with ObservableOfOptionSyntax
    with SignalSyntax
    with SignalOfEitherSyntax
    with SignalOfOptionSyntax
    with SignalOfTuple2Syntax
    with SignalOfOptionOfSignalSyntax
    with SignalOfBooleanSyntax
    with EventStreamSyntax
    with EventStreamOfEitherSyntax
    with EventStreamOfOptionSyntax
    with VarOfBooleanSyntax
    with EventPropTransformationSyntax
    with EventPropSyntax
    with FutureOfEitherSyntax
    with HtmlTagSyntax
    with SvgTagSyntax
    with ReactiveHtmlElementSyntax
    with ReactiveSvgElementSyntax
    with InputElementSyntax
    with TextAreaSyntax
    with OptionOfSignalSyntax
    with SeqSyntax
    with MiscSyntax
    with SequentiallySyntax
    with HtmlEntities
    with ReceiversSyntax {

  type AmAny                                                  = io.laminext.AmAny
  type StoredString                                           = io.laminext.core.StoredString
  type StoredBoolean                                          = io.laminext.core.StoredBoolean
  type AmendedHtmlTag[R <: dom.html.Element, AmType <: AmAny] = io.laminext.AmendedHtmlTag[R, AmType]
  type AmendedSvgTag[R <: dom.svg.Element]                    = io.laminext.AmendedSvgTag[R]

}
