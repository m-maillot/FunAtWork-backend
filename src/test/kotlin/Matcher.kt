import com.fasterxml.jackson.databind.ObjectMapper
import com.natpryce.hamkrest.Matcher
import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.has

import org.http4k.core.HttpMessage

fun hasBodyJson(expected: String): Matcher<HttpMessage> = has("Body", { m: HttpMessage -> ObjectMapper().readTree(m.bodyString()) }, equalTo(ObjectMapper().readTree(expected)))
