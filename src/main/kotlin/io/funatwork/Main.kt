package io.funatwork

import io.funatwork.database.DatabaseHelper


fun main(args: Array<String>) {
    val server = FunServer(8080, DatabaseHelper("/tmp/babyfoot.h2.db"))
    server.start().block()
}