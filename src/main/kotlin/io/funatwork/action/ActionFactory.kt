package io.funatwork.action

import io.funatwork.database.DatabaseHelper

class ActionFactory(val databaseHelper: DatabaseHelper) {

    fun createOrga() =
            OrganizationAction(databaseHelper.organizationResource())
}