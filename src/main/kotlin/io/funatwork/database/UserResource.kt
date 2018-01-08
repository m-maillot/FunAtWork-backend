package io.funatwork.database

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.j256.ormlite.dao.Dao
import io.funatwork.database.entity.TOKEN
import io.funatwork.database.entity.UserEntity
import io.funatwork.database.entity.toEntity
import io.funatwork.database.entity.toModel
import io.funatwork.model.User
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.impl.crypto.MacProvider


open class UserResource(private val entityStore: Dao<UserEntity, String>) {

    val EXPIRATION_TOKEN = 30 * 24 * 3600 * 1000L
    fun create(user: User): User {
        val userEntity = user.toEntity()
        entityStore.create(userEntity)
        return userEntity.toModel()
    }

    fun login(user: User): User {
        val userEntity = user.toEntity().copy(token = createJWT(user.mail), tokenExpireTimestampInMs = System.currentTimeMillis() + EXPIRATION_TOKEN)
        entityStore.update(userEntity)
        return userEntity.toModel()
    }

    //Sample method to construct a JWT
    private fun createJWT(subject: String) =
            Jwts.builder()
                    .setSubject(subject)
                    .signWith(SignatureAlgorithm.HS512, MacProvider.generateKey())
                    .compact()

    fun findWith(token: String?): Result<User, Error> {
        if (token == null) {
            return Err(Error("Missing token"))
        }
        val user = entityStore.queryForEq(TOKEN, token).firstOrNull() ?: return Err(Error("User token not found"))
        if (user.tokenExpireTimestampInMs > System.currentTimeMillis()) {
            return Err(Error("User token expired"))
        }
        return Ok(user.toModel())
    }

    fun list() =
            entityStore.queryForAll().map { it.toModel() }
}