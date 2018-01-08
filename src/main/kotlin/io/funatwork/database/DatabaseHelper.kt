package io.funatwork.database

import com.j256.ormlite.dao.Dao
import com.j256.ormlite.dao.DaoManager
import com.j256.ormlite.jdbc.JdbcConnectionSource
import com.j256.ormlite.table.TableUtils
import io.funatwork.database.entity.OrganizationEntity
import io.funatwork.database.entity.PlayerEntity
import io.funatwork.database.entity.UserEntity
import java.io.File

class DatabaseHelper(databasePathName: String, mode: String = "file", extra: String = "") {

    private val databaseName = if (mode == "file") File(databasePathName).absolutePath else databasePathName
    private val connectionSource = JdbcConnectionSource("jdbc:h2:$mode:$databaseName;$extra")
    private val organisationResource = OrganizationResource(DaoManager.createDao(connectionSource, OrganizationEntity::class.java) as Dao<OrganizationEntity, String>)
    private val playerResource = PlayerResource(DaoManager.createDao(connectionSource, PlayerEntity::class.java) as Dao<PlayerEntity, String>)
    private val userResource = UserResource(DaoManager.createDao(connectionSource, UserEntity::class.java) as Dao<UserEntity, String>)

    init {
        // create table
        TableUtils.createTableIfNotExists(connectionSource, OrganizationEntity::class.java)
        TableUtils.createTableIfNotExists(connectionSource, PlayerEntity::class.java)
        TableUtils.createTableIfNotExists(connectionSource, UserEntity::class.java)
    }

    fun organizationResource() = organisationResource
    fun playerResource() = playerResource
    fun userResource() = userResource

    fun close() = connectionSource.close()
}