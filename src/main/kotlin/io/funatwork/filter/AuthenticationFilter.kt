package io.funatwork.filter

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import io.funatwork.database.UserResource
import org.http4k.core.Filter
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.core.with
import org.http4k.lens.RequestContextLens

class AuthenticationFilter(private val key: RequestContextLens<AuthUser>, private val userResource: UserResource) {

    operator fun invoke() = Filter { next ->
        { request ->
                val codeReturn = userResource.findWith(request.header("Authorization"))
                when (codeReturn) {
                    is Err -> Response(Status.UNAUTHORIZED)
                    is Ok -> next(request.with(key of codeReturn.value.toAuthUser()))
                }
        }
    }

}