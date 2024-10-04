package ru.itmo.starshipsservice.controller

import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.client.ClientBuilder
import jakarta.ws.rs.client.Entity
import jakarta.ws.rs.core.Response
import java.util.*

@Path("/starships")
class StarshipsController {

    @POST
    @Path("/create/{starship-id}/{starship-name}")
    fun saveStarship(
        @PathParam("starship-id") starshipId: UUID,
        @PathParam("starship-name") starshipName: String,
    ): Response {
        val client = ClientBuilder.newClient()
        return client
            .target("$SPACE_MARINES_SERVICE_URL/starships/create/$starshipId/$starshipName")
            .request()
            .post(Entity.text(""))
    }

    @POST
    @Path("/{starship-id}/load/{space-marine-id}")
    fun loadSpaceMarine(
        @PathParam("starship-id") starshipId: UUID,
        @PathParam("space-marine-id") spaceMarineId: Long,
    ): Response {
        val client = ClientBuilder.newClient()
        return client
            .target("$SPACE_MARINES_SERVICE_URL/starships/$starshipId/load/$spaceMarineId")
            .request()
            .post(Entity.text(""))
    }

    companion object {
        private const val SPACE_MARINES_SERVICE_URL = "http://localhost:8080/space-marines-service"
    }
}