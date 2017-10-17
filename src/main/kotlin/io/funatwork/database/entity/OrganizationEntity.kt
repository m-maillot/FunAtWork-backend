package io.funatwork.database.entity

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

@DatabaseTable(tableName = "organization")
data class OrganizationEntity(
        @DatabaseField(generatedId = true)
        var id: Int? = null,
        @DatabaseField
        var name: String = "",
        @DatabaseField
        var logo: String = ""
)