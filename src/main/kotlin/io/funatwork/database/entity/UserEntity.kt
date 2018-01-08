package io.funatwork.database.entity

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

@DatabaseTable(tableName = "user")
data class UserEntity(
        @DatabaseField(generatedId = true)
        val id: Int = -1,
        @DatabaseField(columnName = MAIL, canBeNull = false)
        val mail: String = "",
        @DatabaseField(columnName = PASSWORD, canBeNull = true)
        val password: String? = null,
        @DatabaseField(columnName = TOKEN, canBeNull = false)
        val token: String = "",
        @DatabaseField(columnName = TOKEN_EXPIRATION, canBeNull = false)
        val tokenExpireTimestampInMs: Long = -1,

        @DatabaseField(canBeNull = false, foreign = true)
        var organization: OrganizationEntity = OrganizationEntity(name = "unknown", logo = "")
)

const val MAIL = "mail"
const val PASSWORD = "password"
const val TOKEN = "token"
const val TOKEN_EXPIRATION = "tokenExpirationTimestampInMs"