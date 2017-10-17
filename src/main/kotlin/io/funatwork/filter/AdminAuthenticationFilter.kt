package io.funatwork.filter

import org.http4k.core.Filter
import org.http4k.core.Response
import org.http4k.core.Status

object AdminAuthenticationFilter {

    operator fun invoke() = Filter { next ->
        { request ->
            if (request.header("Authorization") == null) {
                Response(Status.UNAUTHORIZED)
            } else {
                next(request)
            }
        }
    }
}