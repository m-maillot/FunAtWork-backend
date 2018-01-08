package io.funatwork.database

import com.j256.ormlite.dao.Dao
import io.funatwork.database.entity.*
import io.funatwork.model.Player
import io.funatwork.model.User

open class PlayerResource(private val entityStore: Dao<PlayerEntity, String>) {

    fun create(player: Player): Player {
        val playerEntity = player.toEntity()
        entityStore.create(playerEntity)
        return playerEntity.toModel()
    }
}