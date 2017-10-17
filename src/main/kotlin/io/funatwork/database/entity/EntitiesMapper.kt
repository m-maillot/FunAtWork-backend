package io.funatwork.database.entity

import io.funatwork.model.Organization

fun OrganizationEntity.toModel() =
        Organization(id = id ?: 0,
                name = name,
                logo = logo)