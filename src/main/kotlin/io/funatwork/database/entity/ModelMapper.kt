package io.funatwork.database.entity

import io.funatwork.model.Organization
import io.funatwork.model.User

fun User.toEntity() =
        UserEntity(id = id,
                login = login,
                password = password,
                token = token,
                tokenExpire = tokenExpire,
                organization = organization.toEntity())

fun Organization.toEntity() =
        OrganizationEntity(id = id,
                logo = logo,
                name = name
        )