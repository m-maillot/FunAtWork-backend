package io.funatwork.route

import io.funatwork.action.ActionFactory
import io.funatwork.action.OrganizationAction
import io.funatwork.action.RegisterAction
import io.funatwork.action.input.RegisterInput
import io.funatwork.action.output.RegisterOutput
import io.funatwork.filter.AdminAuthenticationFilter
import io.funatwork.filter.AuthenticationFilter
import org.http4k.contract.*
import org.http4k.core.Method.GET
import org.http4k.core.Method.POST
import org.http4k.core.Response
import org.http4k.core.Status.Companion.BAD_REQUEST
import org.http4k.core.Status.Companion.CONFLICT
import org.http4k.core.Status.Companion.CREATED
import org.http4k.core.Status.Companion.UNAUTHORIZED
import org.http4k.core.then
import org.http4k.core.with
import org.http4k.format.Gson
import org.http4k.lens.Header
import org.http4k.lens.Query
import org.http4k.lens.int
import org.http4k.routing.bind
import org.http4k.routing.routes

class RouteFactory(private val actionFactory: ActionFactory,
                   private val adminFilter: AdminAuthenticationFilter,
                   private val authFilter: AuthenticationFilter) {

    fun create() =
            routes(
                    "/api/v1" bind contract(OpenApi(ApiInfo("My great API", "v1.0"), Gson), "/docs/swagger.json", ApiKey(Query.int().required("api"), { it == 42 }),
                            createRegisterRoutes(actionFactory.createRegister()) to actionFactory.createRegister().register()
                    )
            )


    private fun createOrganizationRoutes(organizationAction: OrganizationAction) =
            routes(
                    "/organizations" bind GET to authFilter.invoke().then(organizationAction.select()),
                    "/organizations" bind POST to adminFilter.invoke().then(organizationAction.create())
            )

    private fun createRegisterRoutes(registerAction: RegisterAction) =
            ("/register" meta {
                body = registerAction.registerInputLens
                description = "Register a new user. "
                headers += Header.required("Authorization", "JWT authentication")
                receiving(registerAction.registerInputLens to RegisterInput.empty())
                returning("User has been created" to Response(CREATED).with(
                        registerAction.registerOutputLens of RegisterOutput.empty()))
                returning("User already exist" to Response(CONFLICT))
                returning("Missing arguements" to Response(BAD_REQUEST))
                returning("Authentication required" to Response(UNAUTHORIZED))
            } bindContract POST)
}