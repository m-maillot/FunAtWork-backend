package io.funatwork.filter

import io.funatwork.model.User

data class AuthUser(val id: Int)

fun User.toAuthUser() =
    AuthUser(id = id)
