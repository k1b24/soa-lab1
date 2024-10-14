package ru.itmo.spacemarinesservice.controller

import com.fasterxml.jackson.databind.exc.MismatchedInputException
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import jakarta.inject.Inject
import jakarta.validation.Validation
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import ru.itmo.spacemarinesservice.model.entity.AstartesCategory
import ru.itmo.spacemarinesservice.model.entity.QueryParams
import ru.itmo.spacemarinesservice.model.entity.SpaceMarine
import ru.itmo.spacemarinesservice.model.request.PostSpaceMarineRequest
import ru.itmo.spacemarinesservice.model.request.SortDirection
import ru.itmo.spacemarinesservice.model.request.SortType
import ru.itmo.spacemarinesservice.model.response.AmountResponse
import ru.itmo.spacemarinesservice.model.response.ErrorResponse
import ru.itmo.spacemarinesservice.model.response.SpaceMarinesResponse
import ru.itmo.spacemarinesservice.service.SpaceMarinesService
import ru.itmo.spacemarinesservice.util.buildErrorResponseByException
import java.io.InputStream
import java.time.LocalDate


@Path("/space-marines")
class SpaceMarinesController {

    @Inject
    private lateinit var spaceMarinesService: SpaceMarinesService

    private var objectMapper = jacksonObjectMapper()

    private var validator = Validation.buildDefaultValidatorFactory().validator

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun saveSpaceMarine(spaceMarinEntityInputStream: InputStream): Response {
        val postSpaceMarineRequest: PostSpaceMarineRequest

        try {
            postSpaceMarineRequest = objectMapper.readValue(spaceMarinEntityInputStream.readAllBytes(), PostSpaceMarineRequest::class.java)
        } catch (e: MismatchedInputException) {
            return Response.status(Response.Status.BAD_REQUEST)
                .entity(
                    ErrorResponse(
                        code = Response.Status.BAD_REQUEST.statusCode,
                        message = "missing: ${e.path.map { it.fieldName }}",
                    )
                )
                .build()
        }

        val constraints = validator.validate(postSpaceMarineRequest)
            .plus(validator.validate(postSpaceMarineRequest.chapter))
            .plus(validator.validate(postSpaceMarineRequest.coordinates))
        if (constraints.isNotEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                .entity(
                    ErrorResponse(
                        code = Response.Status.BAD_REQUEST.statusCode,
                        message = "${constraints.map { "${it.propertyPath} = ${it.message}" }}",
                    )
                )
                .build()
        }

        try {
            val spaceMarine = spaceMarinesService.createSpaceMarine(postSpaceMarineRequest)
            return Response.ok().entity(spaceMarine).build();
        } catch (e: Exception) {
            return buildErrorResponseByException(e)
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getSpaceMarines(
        @QueryParam("limit") limit: Int?,
        @QueryParam("offset") offset: Int?,
        @QueryParam("sortBy") sortBy: Set<String>?,
        @QueryParam("sortDirection") sortDirection: String?,
        @QueryParam("minId") minId: Long?,
        @QueryParam("maxId") maxId: Long?,
        @QueryParam("name") name: String?,
        @QueryParam("minX") minX: Float?,
        @QueryParam("maxX") maxX: Float?,
        @QueryParam("minY") minY: Float?,
        @QueryParam("maxY") maxY: Float?,
        @QueryParam("minCreationDate") minCreationDate: String?,
        @QueryParam("maxCreationDate") maxCreationDate: String?,
        @QueryParam("minHealth") minHealth: Float?,
        @QueryParam("maxHealth") maxHealth: Float?,
        @QueryParam("loyal") loyal: Boolean?,
        @QueryParam("minHeight") minHeight: Double?,
        @QueryParam("maxHeight") maxHeight: Double?,
        @QueryParam("category") category: String?,
        @QueryParam("chapterName") chapterName: String?,
        @QueryParam("chapterWorld") chapterWorld: String?,
    ): Response {
        val queryParams = QueryParams(
            limit,
            offset,
            sortBy?.map {
                try {
                    SortType.valueOf(it)
                } catch (e: Exception) {
                    return Response.status(Response.Status.BAD_REQUEST)
                        .entity(
                            ErrorResponse(
                                code = Response.Status.BAD_REQUEST.statusCode,
                                message = "wrong value of sort by, must be: ${SortType.values().map { value -> value.name }}",
                            )
                        )
                        .build()
                }
            }?.reversed(),
            sortDirection?.let { SortDirection.valueOf(it) },
            minId,
            maxId,
            name,
            minX,
            maxX,
            minY,
            maxY,
            minCreationDate?.let {
                try {
                    LocalDate.parse(it)
                } catch (e: Exception) {
                    return Response.status(Response.Status.BAD_REQUEST)
                        .entity(
                            ErrorResponse(
                                code = Response.Status.BAD_REQUEST.statusCode,
                                message = "wrong value of min creation date",
                            )
                        )
                        .build()
                }
            },
            maxCreationDate?.let {
                try {
                    LocalDate.parse(it)
                } catch (e: Exception) {
                    return Response.status(Response.Status.BAD_REQUEST)
                        .entity(
                            ErrorResponse(
                                code = Response.Status.BAD_REQUEST.statusCode,
                                message = "wrong value of max creation date",
                            )
                        )
                        .build()
                }
            },
            minHealth,
            maxHealth,
            loyal,
            minHeight,
            maxHeight,
            category?.let {
                try {
                    AstartesCategory.valueOf(it)
                } catch (e: Exception) {
                    return Response.status(Response.Status.BAD_REQUEST)
                        .entity(
                            ErrorResponse(
                                code = Response.Status.BAD_REQUEST.statusCode,
                                message = "wrong value of category, must be of: ${AstartesCategory.values().map { value -> value.name }}",
                            )
                        )
                        .build()
                }
            },
            chapterName,
            chapterWorld,
        )
        try {
            val spaceMarines = spaceMarinesService.getSpaceMarines(queryParams)
            return Response.ok().entity(
                SpaceMarinesResponse(
                    data = spaceMarines,
                    total = spaceMarines.size,
                    limit = queryParams.limit,
                    offset = queryParams.offset,
                )
            ).build()
        } catch (e: Exception) {
            return buildErrorResponseByException(e)
        }
    }

    @GET
    @Path("/{space-marine-id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getSpaceMarine(
        @PathParam("space-marine-id") spaceMarineId: Long,
    ): Response {
        val spaceMarine: SpaceMarine?
        try {
            spaceMarine = spaceMarinesService.getSpaceMarine(spaceMarineId)
        } catch (e: Exception) {
            return buildErrorResponseByException(e)
        }
        return if (spaceMarine != null) {
            Response.ok().entity(spaceMarine).build()
        } else {
            Response
                .status(Response.Status.NOT_FOUND)
                .entity(
                    ErrorResponse(
                        code = Response.Status.NOT_FOUND.statusCode,
                        message = "Space marine by id $spaceMarineId was not found",
                    )
                )
                .build()
        }
    }

    @PUT
    @Path("/{space-marine-id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun putSpaceMarine(
        @PathParam("space-marine-id") spaceMarineId: Long,
        spaceMarinEntityInputStream: InputStream,
    ): Response {
        val postSpaceMarineRequest: PostSpaceMarineRequest

        try {
            postSpaceMarineRequest = objectMapper.readValue(spaceMarinEntityInputStream.readAllBytes(), PostSpaceMarineRequest::class.java)
        } catch (e: MismatchedInputException) {
            return Response.status(Response.Status.BAD_REQUEST)
                .entity(
                    ErrorResponse(
                        code = Response.Status.BAD_REQUEST.statusCode,
                        message = "missing: ${e.path.map { it.fieldName }}",
                    )
                )
                .build()
        }

        val constraints = validator.validate(postSpaceMarineRequest)
            .plus(validator.validate(postSpaceMarineRequest.chapter))
            .plus(validator.validate(postSpaceMarineRequest.coordinates))
        if (constraints.isNotEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                .entity(
                    ErrorResponse(
                        code = Response.Status.BAD_REQUEST.statusCode,
                        message = "${constraints.map { "${it.propertyPath} = ${it.message}" }}",
                    )
                )
                .build()
        }

        try {
            val spaceMarine = spaceMarinesService.updateSpaceMarine(spaceMarineId, postSpaceMarineRequest)
            return Response.status(Response.Status.NO_CONTENT).build()
        } catch (e: Exception) {
            return buildErrorResponseByException(e)
        }
    }

    @DELETE
    @Path("/{space-marine-id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun deleteSpaceMarineById(
        @PathParam("space-marine-id") spaceMarineId: Long,
    ): Response {
        val deletedAmount: Int
        try {
            deletedAmount = spaceMarinesService.deleteSpaceMarineById(spaceMarineId)
        } catch (e: Exception) {
            return buildErrorResponseByException(e)
        }
        if (deletedAmount != 0) {
            return Response.status(Response.Status.NO_CONTENT).build()
        }
        return Response
            .status(Response.Status.NOT_FOUND)
            .entity(
                ErrorResponse(
                    code = Response.Status.NOT_FOUND.statusCode,
                    message = "Space marine by id $spaceMarineId was not found",
                )
            )
            .build()
    }

    @DELETE
    @Path("/categories/{category}")
    @Produces(MediaType.APPLICATION_JSON)
    fun deleteSpaceMarineByCategory(
        @PathParam("category") category: AstartesCategory,
    ): Response {
        val deletedAmount: Int
        try {
            deletedAmount = spaceMarinesService.deleteSpaceMarineByCategory(category)
        } catch (e: Exception) {
            return buildErrorResponseByException(e)
        }
        if (deletedAmount != 0) {
            return Response.status(Response.Status.NO_CONTENT).build()
        }
        return Response
            .status(Response.Status.NOT_FOUND)
            .entity(
                ErrorResponse(
                    code = Response.Status.NOT_FOUND.statusCode,
                    message = "No space marines with category $category was not found",
                )
            )
            .build()
    }

    @GET
    @Path("loyalists")
    @Produces(MediaType.APPLICATION_JSON)
    fun getAnyLoyalSpaceMarine(): Response {
        val spaceMarine: SpaceMarine?
        try {
            spaceMarine = spaceMarinesService.getAnyLoyalSpaceMarine()
        } catch (e: Exception) {
            return buildErrorResponseByException(e)
        }
        return if (spaceMarine != null) {
            Response.ok().entity(spaceMarine).build()
        } else {
            Response
                .status(Response.Status.NOT_FOUND)
                .entity(
                    ErrorResponse(
                        code = Response.Status.NOT_FOUND.statusCode,
                        message = "Верных десантников не осталось (((((((",
                    )
                )
                .build()
        }
    }

    @GET
    @Path("amount")
    @Produces(MediaType.APPLICATION_JSON)
    fun getHealthySpaceMarinesAmount(
        @QueryParam("minHealth") minHealth: Float?,
    ): Response {
        if (minHealth == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                .entity(
                    ErrorResponse(
                        code = Response.Status.BAD_REQUEST.statusCode,
                        message = "minHealth query param must be present",
                    )
                )
                .build()
        }
        val amount: Long
        try {
            amount = spaceMarinesService.getAmountOfHealthySpaceMarines(minHealth)
        } catch (e: Exception) {
            return buildErrorResponseByException(e)
        }
        return if (amount != 0L)
            Response.ok().entity(AmountResponse(amount)).build()
        else
            Response.status(Response.Status.NO_CONTENT).build()
    }
}
