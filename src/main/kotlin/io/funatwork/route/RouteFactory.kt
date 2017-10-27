package io.funatwork.route

import io.funatwork.action.ActionFactory
import io.funatwork.action.OrganizationAction
import io.funatwork.action.PlayerAction
import io.funatwork.filter.AdminAuthenticationFilter
import io.funatwork.filter.AuthenticationFilter
import org.http4k.core.Method.GET
import org.http4k.core.Method.POST
import org.http4k.core.then
import org.http4k.routing.bind
import org.http4k.routing.routes

class RouteFactory(private val actionFactory: ActionFactory,
                   private val adminFilter: AdminAuthenticationFilter,
                   private val authFilter: AuthenticationFilter) {

    fun create() =
            routes(createOrganizationRoutes(actionFactory.createOrga()))
                    .withBasePath("/api/v1")

    private fun createOrganizationRoutes(organizationAction: OrganizationAction) =
            routes(
                    "/organizations" bind GET to authFilter.invoke().then(organizationAction.select()),
                    "/organizations" bind POST to adminFilter.invoke().then(organizationAction.create())
            )

    private fun createPlayerRoutes(playerAction: PlayerAction) =
            routes(
                    "/signin" bind GET to playerAction.signin(),
                    createPlayerAdminRoutes(playerAction),
                    createPlayerAuthRoutes(playerAction)
            ).withBasePath("/api/v1")

    private fun createPlayerAdminRoutes(playerAction: PlayerAction) =
            routes(
                    "/players" bind POST to playerAction.create()
            ).withFilter(authFilter.invoke())

    private fun createPlayerAuthRoutes(playerAction: PlayerAction) =
            routes(
                    "/players" bind GET to playerAction.select()
            ).withFilter(adminFilter.invoke())
}