package io.laminext.syntax

import io.laminext.util.SmartClass
import org.scalajs.dom

trait CoreSyntax
    extends ObservableSyntax
    with ObservableOfBooleanSyntax
    with ObservableOfOptionSyntax
    with SignalSyntax
    with SignalOfEitherSyntax
    with SignalOfOptionSyntax
    with SignalOfTuple2Syntax
    with SignalOfOptionOfSignalSyntax
    with SignalOfBooleanSyntax
    with EventStreamSyntax
    with EventStreamOfUnitSyntax
    with EventStreamOfEitherSyntax
    with EventStreamOfOptionSyntax
    with VarOfBooleanSyntax
    with EventPropTransformationSyntax
    with ReactiveEventPropSyntax
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
    with HtmlEntities
    with SmartClass
    with ReceiversSyntax {

  type AmAny                                                  = io.laminext.AmAny
  type StoredString                                           = io.laminext.core.StoredString
  type StoredBoolean                                          = io.laminext.core.StoredBoolean
  type AmendedHtmlTag[R <: dom.html.Element, AmType <: AmAny] = io.laminext.AmendedHtmlTag[R, AmType]
  type AmendedSvgTag[R <: dom.svg.Element]                    = io.laminext.AmendedSvgTag[R]

}
