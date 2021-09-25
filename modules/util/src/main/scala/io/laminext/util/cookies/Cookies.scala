package io.laminext.util.cookies

import scala.scalajs.js
import scala.scalajs.js.Date
import scala.scalajs.js.URIUtils._
import scala.util.matching.Regex

object Cookies {

  def list(include: Option[Set[String]]): Map[String, String] = {
    if (js.isUndefined(org.scalajs.dom.document.cookie)) {
      Map.empty
    } else {
      org.scalajs.dom.document.cookie
        .split("; ")
        .toList
        .view
        .map { cookie =>
          cookie.split("=", 2)
        }
        .collect { case Array(nameRaw, valueRaw) =>
          val name = decode(nameRaw)
          name -> valueRaw
        }
        .collect {
          case (name, valueRaw) if include.forall(_.contains(name)) =>
            val value = decode(valueRaw)
            name -> value
        }
        .to(Map)
    }
  }

  private val decodeRegex                                     = "(%[0-9A-Z]{2})+".r
  private val valueUriComponentsRegex                         = "%(23|24|26|2B|3A|3C|3E|3D|2F|3F|40|5B|5D|5E|60|7B|7D|7C)".r
  private val keyUriComponentsRegex                           = "%(23|24|26|2B|5E|60|7C)".r

  def set(
    key: String,
    value: String,
    ttlSeconds: Option[Int] = None,
    path: String = "/",
    domain: Option[String] = null,
    secure: Boolean = false
  ): String = {
    val attributes  = extend(
      Map(
        "path"    -> Some("/")
      ),
      Map(
        "expires" -> ttlSeconds
          .map { e =>
            new Date(Date.now() + e * 1000).toUTCString()
          }
          .orElse(Some("")),
        "path"    -> Some(path),
        "domain"  -> domain,
        "secure"  -> Some(secure.toString),
        "value"   -> Some(value).map { s =>
          valueUriComponentsRegex.replaceAllIn(
            encodeURIComponent(s),
            (m: Regex.Match) => decodeURIComponent(m.group(0))
          )
        }
      )
    )
    val preparedKey =
      keyUriComponentsRegex
        .replaceAllIn(
          encodeURIComponent(key),
          (m: Regex.Match) => decodeURIComponent(m.group(0))
        )
        .replace("(", "%28")
        .replace(")", "%29")

    val stringifiedAttributes = attributes.foldLeft("") { case (acc, (k, v)) =>
      s"$acc; $k=${v.split(";").headOption.getOrElse("")}"
    }
    val cookie                = s"$preparedKey=$value$stringifiedAttributes"
    org.scalajs.dom.document.cookie = cookie
    cookie
  }

  def remove(key: String): String                                                  =
    set(key, "", ttlSeconds = Some(-1))

  private def extend(arguments: Map[String, Option[String]]*): Map[String, String] = {
    arguments.foldLeft(Map.empty[String, String]) { case (acc, next) =>
      acc ++ next.collect { case (key, Some(value)) => key -> value }
    }
  }

  private def decode(s: String): String                                            =
    decodeRegex.replaceAllIn(s, (m: Regex.Match) => decodeURIComponent(m.group(0)))

}
