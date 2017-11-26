package io.funatwork.database.entity

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

@DatabaseTable(tableName = "user")
data class UserEntity(
        @DatabaseField(generatedId = true)
        val id: Int? = null,
        @DatabaseField(columnName = MAIL, canBeNull = false)
        val mail: String = "",
        @DatabaseField(columnName = PASSWORD, canBeNull = true)
        val password: String? = null,
        @DatabaseField(columnName = TOKEN, canBeNull = true)
        val token: String? = null,
        @DatabaseField(columnName = TOKEN_EXPIRATION, canBeNull = true)
        val tokenExpireTimestampInMs: Long? = null,

        @DatabaseField(canBeNull = false, foreign = true)
        var organization: OrganizationEntity = OrganizationEntity(name = "unknown", logo = "")
)

const val MAIL = "mail"
const val PASSWORD = "password"
const val TOKEN = "token"
const val TOKEN_EXPIRATION = "tokenExpirationTimestampInMs"