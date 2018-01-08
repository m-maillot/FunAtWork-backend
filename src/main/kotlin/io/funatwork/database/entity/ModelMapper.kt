package io.funatwork.database.entity

import io.funatwork.model.Organization
import io.funatwork.model.Player
import io.funatwork.model.User

fun User.toEntity() =
        UserEntity(id = id,
                mail = mail,
                password = password,
                token = token,
                tokenExpireTimestampInMs = tokenExpireTimestampInMs,
                organization = organization.toEntity())

fun Organization.toEntity() =
        OrganizationEntity(id = id,
                logo = logo,
                name = name
        )

fun Player.toEntity() =
        PlayerEntity(id = id,
                pseudo = pseudo,
                avatar = avatar,
                user = user.toEntity())