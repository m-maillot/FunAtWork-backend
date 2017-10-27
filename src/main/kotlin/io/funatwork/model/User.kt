package io.funatwork.model

data class User(val id: Int = 0,
                val login: String,
                val password: String,
                val token: String,
                val tokenExpire: String,
                var organization: Organization)