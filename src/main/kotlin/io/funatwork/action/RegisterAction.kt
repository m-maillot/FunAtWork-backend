package io.funatwork.action

import io.funatwork.action.input.RegisterInput
import io.funatwork.action.output.RegisterOutput
import io.funatwork.database.OrganizationResource
import io.funatwork.database.PlayerResource
import io.funatwork.database.UserResource
import io.funatwork.model.Organization
import io.funatwork.model.Player
import io.funatwork.model.User
import org.http4k.core.*
import org.http4k.format.Gson.auto

class RegisterAction(private val organizationResource: OrganizationResource, private val userResource: UserResource, private val playerResource: PlayerResource) {

    val registerInputLens = Body.auto<RegisterInput>().toLens()
    val registerOutputLens = Body.auto<RegisterOutput>().toLens()

    fun register(): HttpHandler = { request ->
        Response(Status.OK).with(
                registerOutputLens of
                        registerNewUserWithOrganization(
                                registerInputLens(request)))
    }

    private fun registerNewUserWithOrganization(registerInput: RegisterInput): RegisterOutput {
        val orga = organizationResource.create(Organization(name = registerInput.organizationName, logo = ""))
        val user = userResource.create(User(mail = registerInput.mail, password = registerInput.mail, organization = orga))
        val userLogged = userResource.login(user)
        val player = playerResource.create(Player(pseudo = registerInput.pseudo, avatar = registerInput.avatar, user = user))
        return RegisterOutput(id = player.id, pseudo = player.pseudo, organization = orga, avatar = player.avatar, token = userLogged.token, tokenExpireTimestampInMs = userLogged.tokenExpireTimestampInMs)
    }
}