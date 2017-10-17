package io.funatwork.database

import com.j256.ormlite.dao.Dao
import com.j256.ormlite.dao.DaoManager
import com.j256.ormlite.jdbc.JdbcConnectionSource
import com.j256.ormlite.table.TableUtils
import io.funatwork.database.entity.OrganizationEntity
import java.io.File

class DatabaseHelper(databasePathName: String) {

    private val connectionSource = JdbcConnectionSource("jdbc:h2:file:"
            + File(databasePathName).absolutePath + ".h2.db")
    private val organisationResource = OrganizationResource(DaoManager.createDao(connectionSource, OrganizationEntity::class.java) as Dao<OrganizationEntity, String>)

    init {
        // create table
        TableUtils.createTableIfNotExists(connectionSource, OrganizationEntity::class.java)
    }

    fun organizationResource() = organisationResource
}