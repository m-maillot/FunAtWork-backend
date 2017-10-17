package io.funatwork

import io.funatwork.action.OrganizationAction
import io.funatwork.database.DatabaseHelper
import io.funatwork.route.createOrganizationRoutes
import org.http4k.core.then
import org.http4k.filter.CorsPolicy
import org.http4k.filter.DebuggingFilters
import org.http4k.filter.ServerFilters
import org.http4k.routing.routes
import org.http4k.server.Jetty
import org.http4k.server.asServer

class FunServer(port: Int, private val databaseHelper: DatabaseHelper) {

    private val server = DebuggingFilters.PrintRequestAndResponse()
            .then(ServerFilters.Cors(CorsPolicy.UnsafeGlobalPermissive))
            .then(createRoutes())
            .asServer(Jetty(port))

    fun stop() =
            server.stop()

    fun start() =
            server.start()

    private fun createRoutes() =
            routes(createOrganizationRoutes(OrganizationAction(databaseHelper.organizationResource())))
}