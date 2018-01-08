package io.funatwork.model

data class User(val id: Int = 0,
                val mail: String,
                val password: String?,
                val token: String = "",
                val tokenExpireTimestampInMs: Long = -1,
                var organization: Organization)