package ru.itmo.spacemarinesservice.controller

import jakarta.inject.Inject
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.core.Response
import ru.itmo.spacemarinesservice.model.response.ErrorResponse
import ru.itmo.spacemarinesservice.service.StarshipsService
import ru.itmo.spacemarinesservice.util.buildErrorResponseByException
import java.util.UUID

@Path("/starships")
class StarshipsController {

    @Inject
    private lateinit var starshipsService: StarshipsService

    @POST
    @Path("/create/{starship-id}/{starship-name}")
    fun saveStarship(
        @PathParam("starship-id") starshipId: UUID,
        @PathParam("starship-name") starshipName: String,
    ): Response {
        if (starshipName.isEmpty()) {
            return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(
                    ErrorResponse(
                        code = Response.Status.BAD_REQUEST.statusCode,
                        message = "starship name must be not empty",
                    )
                )
                .build()
        }
        try {
            starshipsService.saveStarship(starshipId, starshipName)
            return Response.status(Response.Status.NO_CONTENT).build()
        } catch (e: Exception) {
            return buildErrorResponseByException(e)
        }
    }

    @POST
    @Path("/{starship-id}/load/{space-marine-id}")
    fun loadSpaceMarine(
        @PathParam("starship-id") starshipId: UUID,
        @PathParam("space-marine-id") spaceMarineId: Long,
    ): Response {
        try {
            starshipsService.loadSpaceMarine(starshipId, spaceMarineId)
            return Response.status(Response.Status.NO_CONTENT).build()
        } catch (e: Exception) {
            return buildErrorResponseByException(e)
        }
    }
}