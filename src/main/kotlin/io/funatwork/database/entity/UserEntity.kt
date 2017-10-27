package io.funatwork.database.entity

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

@DatabaseTable(tableName = "user")
data class UserEntity(
        @DatabaseField(generatedId = true)
        val id: Int? = null,
        @DatabaseField(columnName = LOGIN)
        val login: String = "",
        @DatabaseField
        val password: String = "",
        @DatabaseField(columnName = TOKEN)
        val token: String = "",
        @DatabaseField
        val tokenExpire: String = "",

        @DatabaseField(canBeNull = false, foreign = true)
        var organization: OrganizationEntity = OrganizationEntity(name = "unknown", logo = "")
)

const val LOGIN = "login"
const val TOKEN = "token"