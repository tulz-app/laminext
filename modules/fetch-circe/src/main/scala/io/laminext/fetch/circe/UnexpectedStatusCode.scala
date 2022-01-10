package io.laminext.fetch.circe

class UnexpectedStatusCode(statusCode: Int) extends Exception(s"Unexpected status code: $statusCode")
