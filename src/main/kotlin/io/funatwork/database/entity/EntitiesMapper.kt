package io.funatwork.database.entity

import io.funatwork.model.Organization
import io.funatwork.model.Player
import io.funatwork.model.User

fun OrganizationEntity.toModel() =
        Organization(id = id ?: 0,
                name = name,
                logo = logo)

fun UserEntity.toModel() =
        User(id = id,
                mail = mail,
                organization = organization.toModel(),
                password = password,
                token = token,
                tokenExpireTimestampInMs = tokenExpireTimestampInMs)

fun PlayerEntity.toModel() =
        Player(id = id,
                pseudo = pseudo,
                avatar = avatar,
                user = user.toModel())