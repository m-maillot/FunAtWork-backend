package io.funatwork.filter

import io.funatwork.database.UserResource
import org.http4k.core.Filter
import org.http4k.core.Response
import org.http4k.core.Status

class AuthenticationFilter(private val userResource: UserResource) {

    operator fun invoke() = Filter { next ->
        { request ->
            if (!checkAuthKey(request.header("Authorization"))) {
                Response(Status.UNAUTHORIZED)
            } else {
                next(request)
            }
        }
    }

    fun checkAuthKey(authKey: String?): Boolean {
        return authKey != null && userResource.findWith(authKey) != null
    }
}