package io.funatwork.database

import io.funatwork.model.Player

class PlayerDatabase() {

    fun getAll() =
            listOf(Player(1, "john", "smith"),
                    Player(1, "john", "smith"),
                    Player(1, "john", "smith"))

}