package io.funatwork.database.entity

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

@DatabaseTable(tableName = "player")
data class PlayerEntity(
        @DatabaseField(generatedId = true)
        val id: Int = -1,
        @DatabaseField(columnName = PSEUDO, canBeNull = false)
        val pseudo: String = "",
        @DatabaseField(columnName = AVATAR, canBeNull = true)
        val avatar: String? = null,
        @DatabaseField(canBeNull = false, foreign = true)
        var user: UserEntity = UserEntity()
)

const val PSEUDO = "pseudo"
const val AVATAR = "avatar"