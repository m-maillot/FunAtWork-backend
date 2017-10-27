package io.funatwork.database

import com.j256.ormlite.dao.Dao
import io.funatwork.database.entity.TOKEN
import io.funatwork.database.entity.UserEntity
import io.funatwork.database.entity.toEntity
import io.funatwork.database.entity.toModel
import io.funatwork.model.User

open class UserResource(private val entityStore: Dao<UserEntity, String>) {

    fun create(user: User): User {
        val userEntity = user.toEntity()
        entityStore.create(userEntity)
        return userEntity.toModel()
    }

    fun findWith(token: String) =
            entityStore.queryForEq(TOKEN, token).firstOrNull()

    fun list() =
            entityStore.queryForAll().map { it.toModel() }
}