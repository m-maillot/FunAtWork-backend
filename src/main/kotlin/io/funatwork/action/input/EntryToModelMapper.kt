import io.funatwork.action.input.CreateOrganizationEntry
import io.funatwork.model.Organization

fun CreateOrganizationEntry.toModel() =
        Organization(name = name,
                logo = logo)