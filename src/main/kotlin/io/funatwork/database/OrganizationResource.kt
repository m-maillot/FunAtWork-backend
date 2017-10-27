package io.funatwork.database

import com.j256.ormlite.dao.Dao
import io.funatwork.database.entity.OrganizationEntity
import io.funatwork.database.entity.toModel
import io.funatwork.model.Organization

open class OrganizationResource(private val entityStore: Dao<OrganizationEntity, String>) {

    fun create(organization: Organization): Organization {
        val orga = OrganizationEntity(id = null, name = organization.name, logo = organization.logo)
        entityStore.create(orga)
        return orga.toModel()
    }

    fun list() =
            entityStore.queryForAll().map { it.toModel() }
}