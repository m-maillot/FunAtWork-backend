package io.funatwork.action

import io.funatwork.database.PlayerDatabase
import org.http4k.core.HttpHandler
import org.http4k.core.Response
import org.http4k.core.Status

class PlayerAction(val playerDatabase: PlayerDatabase) {

    fun signin(): HttpHandler = { request ->
        Response(Status.OK).body("signin")
    }

    fun select(): HttpHandler = { request ->
        Response(Status.OK).body("jsonPlayers")
    }

    fun create(): HttpHandler = { request ->
        Response(Status.OK).body("create")
    }
}