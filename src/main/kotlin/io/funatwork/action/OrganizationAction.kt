package io.funatwork.action

import io.funatwork.action.input.CreateOrganizationEntry
import io.funatwork.database.OrganizationResource
import io.funatwork.model.Organization
import org.http4k.core.*
import org.http4k.format.Jackson.auto
import toModel

class OrganizationAction(private val organizationResource: OrganizationResource) {


    private val organizationInputLens = Body.auto<CreateOrganizationEntry>().toLens()
    private val organizationOutputLens = Body.auto<Organization>().toLens()
    private val organizationOutputListLens = Body.auto<List<Organization>>().toLens()

    fun select(): HttpHandler = {
        Response(Status.OK).with(organizationOutputListLens of organizationResource.list())
    }

    fun create(): HttpHandler = { request ->
        Response(Status.OK).with(
                organizationOutputLens of
                        organizationResource.create(
                                organizationInputLens(request).toModel()))
    }
}