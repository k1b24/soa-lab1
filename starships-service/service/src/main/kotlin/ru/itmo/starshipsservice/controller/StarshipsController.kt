package ru.itmo.starshipsservice.controller

import jakarta.ejb.EJB
import jakarta.inject.Inject
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.client.ClientBuilder
import jakarta.ws.rs.client.Entity
import jakarta.ws.rs.core.Response
import ru.itmo.service.StarshipsClientInterface
import java.util.*

@Path("/starships")
class StarshipsController {

    @EJB
    private lateinit var starshipsClient: StarshipsClientInterface


    @POST
    @Path("/create/{starship-id}/{starship-name}")
    fun saveStarship(
        @PathParam("starship-id") starshipId: UUID,
        @PathParam("starship-name") starshipName: String,
    ): Response = starshipsClient.saveStarship(starshipId, starshipName).let {
        Response.status(it.status).build()
    }

    @POST
    @Path("/{starship-id}/load/{space-marine-id}")
    fun loadSpaceMarine(
        @PathParam("starship-id") starshipId: UUID,
        @PathParam("space-marine-id") spaceMarineId: Long,
    ): Response = starshipsClient.loadSpaceMarine(starshipId, spaceMarineId).let {
        Response.status(it.status).build()
    }
}