package io.funatwork.action.output

import io.funatwork.model.Organization

data class RegisterOutput(val id: Int, val pseudo: String, val avatar: String?, val token: String, val tokenExpireTimestampInMs: Long, val organization: Organization) {
    companion object {
        fun empty() = RegisterOutput(0, "", "", "", 0L, Organization(0, "", ""))
    }
}