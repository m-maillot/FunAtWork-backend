package io.funatwork.database.entity

import io.funatwork.model.Organization
import io.funatwork.model.User

fun OrganizationEntity.toModel() =
        Organization(id = id ?: 0,
                name = name,
                logo = logo)

fun UserEntity.toModel() =
        User(id = id ?: 0,
                login = login,
                organization = organization.toModel(),
                password = password,
                token = token,
                tokenExpire = tokenExpire)