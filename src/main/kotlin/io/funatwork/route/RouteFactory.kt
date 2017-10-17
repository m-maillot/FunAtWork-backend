package io.funatwork.route

import io.funatwork.action.OrganizationAction
import io.funatwork.action.PlayerAction
import io.funatwork.filter.AdminAuthenticationFilter
import io.funatwork.filter.AuthenticationFilter
import org.http4k.core.Method.GET
import org.http4k.core.Method.POST
import org.http4k.routing.bind
import org.http4k.routing.routes

fun createOrganizationRoutes(organizationAction: OrganizationAction) =
        routes(
                "/organizations" bind GET to organizationAction.select(),
                "/organizations" bind POST to organizationAction.create()
        )

fun createPlayerRoutes(playerAction: PlayerAction) =
        routes(
                "/signin" bind GET to playerAction.signin(),
                createPlayerAdminRoutes(playerAction),
                createPlayerAuthRoutes(playerAction)
        ).withBasePath("/api/v1")

fun createPlayerAdminRoutes(playerAction: PlayerAction) =
        routes(
                "/players" bind POST to playerAction.create()
        ).withFilter(AuthenticationFilter())

fun createPlayerAuthRoutes(playerAction: PlayerAction) =
        routes(
                "/players" bind GET to playerAction.select()
        ).withFilter(AdminAuthenticationFilter())