package io.funatwork

import io.funatwork.action.ActionFactory
import io.funatwork.database.DatabaseHelper
import io.funatwork.filter.AdminAuthenticationFilter
import io.funatwork.filter.AuthenticationFilter
import io.funatwork.route.RouteFactory
import org.http4k.core.then
import org.http4k.filter.CorsPolicy
import org.http4k.filter.DebuggingFilters
import org.http4k.filter.ServerFilters
import org.http4k.server.Jetty
import org.http4k.server.asServer

class FunServer(port: Int, private val databaseHelper: DatabaseHelper,
                private val adminFilter: AdminAuthenticationFilter = AdminAuthenticationFilter(),
                private val authFilter: AuthenticationFilter = AuthenticationFilter(databaseHelper.userResource),
                private val actionFactory: ActionFactory = ActionFactory(databaseHelper),
                private val routeFactory: RouteFactory = RouteFactory(actionFactory, adminFilter, authFilter)) {

    private val server = DebuggingFilters.PrintRequestAndResponse()
            .then(ServerFilters.Cors(CorsPolicy.UnsafeGlobalPermissive))
            .then(createRoutes())
            .asServer(Jetty(port))


    fun stop() =
            server.stop()

    fun start() =
            server.start()

    private fun createRoutes() =
            routeFactory.create()
}