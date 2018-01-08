package io.funatwork.action

import io.funatwork.database.DatabaseHelper

class ActionFactory(val databaseHelper: DatabaseHelper) {

    fun createOrga() =
            OrganizationAction(databaseHelper.organizationResource())

    fun createRegister() =
            RegisterAction(organizationResource = databaseHelper.organizationResource(),
                    playerResource = databaseHelper.playerResource(),
                    userResource = databaseHelper.userResource())
}