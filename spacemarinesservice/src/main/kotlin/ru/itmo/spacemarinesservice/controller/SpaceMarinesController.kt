package ru.itmo.spacemarinesservice.controller

import com.fasterxml.jackson.databind.exc.MismatchedInputException
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import jakarta.validation.Validation
import jakarta.websocket.server.PathParam
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.itmo.spacemarinesservice.model.body.AstartesCategory
import ru.itmo.spacemarinesservice.model.body.QueryParams
import ru.itmo.spacemarinesservice.model.body.SpaceMarine
import ru.itmo.spacemarinesservice.model.request.PostSpaceMarineRequest
import ru.itmo.spacemarinesservice.model.request.SortDirection
import ru.itmo.spacemarinesservice.model.request.SortType
import ru.itmo.spacemarinesservice.model.ResponseEntity.AmountResponseEntity
import ru.itmo.spacemarinesservice.model.ResponseEntity.ErrorResponseEntity
import ru.itmo.spacemarinesservice.model.ResponseEntity.SpaceMarinesResponseEntity
import ru.itmo.spacemarinesservice.service.SpaceMarinesService
import ru.itmo.spacemarinesservice.util.buildErrorResponseEntityByException
import java.time.LocalDate


@RestController
@RequestMapping("/space-marines")
class SpaceMarinesController(
    private val spaceMarinesService: SpaceMarinesService
) {
    private var validator = Validation.buildDefaultValidatorFactory().validator

    @PostMapping
    @CrossOrigin("*")
    fun saveSpaceMarine(@RequestBody postSpaceMarineRequest: PostSpaceMarineRequest): ResponseEntity<Any> {
        val constraints = validator.validate(postSpaceMarineRequest)
            .plus(validator.validate(postSpaceMarineRequest.chapter))
            .plus(validator.validate(postSpaceMarineRequest.coordinates))
        if (constraints.isNotEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                    ErrorResponseEntity(
                        code = HttpStatus.BAD_REQUEST.value(),
                        message = "${constraints.map { "${it.propertyPath} = ${it.message}" }}",
                    )
                )
        }

        try {
            val spaceMarine = spaceMarinesService.createSpaceMarine(postSpaceMarineRequest)
            return ResponseEntity.ok().body(spaceMarine)
        } catch (e: Exception) {
            return buildErrorResponseEntityByException(e)
        }
    }

    @GetMapping
    @CrossOrigin("*")
    fun getSpaceMarines(
        @RequestParam("limit") limit: Int?,
        @RequestParam("offset") offset: Int?,
        @RequestParam("sortBy") sortBy: Set<String>?,
        @RequestParam("sortDirection") sortDirection: String?,
        @RequestParam("minId") minId: Long?,
        @RequestParam("maxId") maxId: Long?,
        @RequestParam("name") name: String?,
        @RequestParam("minX") minX: Float?,
        @RequestParam("maxX") maxX: Float?,
        @RequestParam("minY") minY: Float?,
        @RequestParam("maxY") maxY: Float?,
        @RequestParam("minCreationDate") minCreationDate: String?,
        @RequestParam("maxCreationDate") maxCreationDate: String?,
        @RequestParam("minHealth") minHealth: Float?,
        @RequestParam("maxHealth") maxHealth: Float?,
        @RequestParam("loyal") loyal: Boolean?,
        @RequestParam("minHeight") minHeight: Double?,
        @RequestParam("maxHeight") maxHeight: Double?,
        @RequestParam("category") category: String?,
        @RequestParam("chapterName") chapterName: String?,
        @RequestParam("chapterWorld") chapterWorld: String?,
    ): ResponseEntity<Any> {
        val queryParams = QueryParams(
            limit,
            offset,
            sortBy?.map {
                try {
                    SortType.valueOf(it)
                } catch (e: Exception) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(
                            ErrorResponseEntity(
                                code = HttpStatus.BAD_REQUEST.value(),
                                message = "wrong value of sort by, must be: ${SortType.entries.map { value -> value.name }}",
                            )
                        )
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
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(
                            ErrorResponseEntity(
                                code = HttpStatus.BAD_REQUEST.value(),
                                message = "wrong value of min creation date",
                            )
                        )
                }
            },
            maxCreationDate?.let {
                try {
                    LocalDate.parse(it)
                } catch (e: Exception) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(
                            ErrorResponseEntity(
                                code = HttpStatus.BAD_REQUEST.value(),
                                message = "wrong value of max creation date",
                            )
                        )
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
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(
                            ErrorResponseEntity(
                                code = HttpStatus.BAD_REQUEST.value(),
                                message = "wrong value of category, must be of: ${AstartesCategory.entries.map { value -> value.name }}",
                            )
                        )
                }
            },
            chapterName,
            chapterWorld,
        )
        try {
            val spaceMarines = spaceMarinesService.getSpaceMarines(queryParams)
            return ResponseEntity.ok().body(
                SpaceMarinesResponseEntity(
                    data = spaceMarines,
                    total = spaceMarines.size,
                    limit = queryParams.limit,
                    offset = queryParams.offset,
                )
            )
        } catch (e: Exception) {
            return buildErrorResponseEntityByException(e)
        }
    }

    @GetMapping("/{space-marine-id}")
    @CrossOrigin("*")
    fun getSpaceMarine(
        @PathVariable("space-marine-id") spaceMarineId: Long,
    ): ResponseEntity<Any> {
        val spaceMarine: SpaceMarine?
        try {
            spaceMarine = spaceMarinesService.getSpaceMarine(spaceMarineId)
        } catch (e: Exception) {
            return buildErrorResponseEntityByException(e)
        }
        return if (spaceMarine != null) {
            ResponseEntity.ok().body(spaceMarine)
        } else {
            ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                    ErrorResponseEntity(
                        code = HttpStatus.NOT_FOUND.value(),
                        message = "Space marine by id $spaceMarineId was not found",
                    )
                )
        }
    }

    @PutMapping("/{space-marine-id}")
    @CrossOrigin("*")
    fun putSpaceMarine(
        @PathVariable("space-marine-id") spaceMarineId: Long,
        @RequestBody postSpaceMarineRequest: PostSpaceMarineRequest,
    ): ResponseEntity<Any> {
        val constraints = validator.validate(postSpaceMarineRequest)
            .plus(validator.validate(postSpaceMarineRequest.chapter))
            .plus(validator.validate(postSpaceMarineRequest.coordinates))
        if (constraints.isNotEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                    ErrorResponseEntity(
                        code = HttpStatus.BAD_REQUEST.value(),
                        message = "${constraints.map { "${it.propertyPath} = ${it.message}" }}",
                    )
                )
        }

        try {
            spaceMarinesService.updateSpaceMarine(spaceMarineId, postSpaceMarineRequest)
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
        } catch (e: Exception) {
            return buildErrorResponseEntityByException(e)
        }
    }

    @DeleteMapping("/{space-marine-id}")
    @CrossOrigin("*")
    fun deleteSpaceMarineById(
        @PathVariable("space-marine-id") spaceMarineId: Long,
    ): ResponseEntity<Any> {
        val deletedAmount: Int
        try {
            deletedAmount = spaceMarinesService.deleteSpaceMarineById(spaceMarineId)
        } catch (e: Exception) {
            return buildErrorResponseEntityByException(e)
        }
        if (deletedAmount != 0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
        }
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(
                ErrorResponseEntity(
                    code = HttpStatus.NOT_FOUND.value(),
                    message = "Space marine by id $spaceMarineId was not found",
                )
            )
    }

    @DeleteMapping("/categories/{category}")
    @CrossOrigin("*")
    fun deleteSpaceMarineByCategory(
        @PathVariable("category") category: AstartesCategory,
    ): ResponseEntity<Any> {
        val deletedAmount: Int
        try {
            deletedAmount = spaceMarinesService.deleteSpaceMarineByCategory(category)
        } catch (e: Exception) {
            return buildErrorResponseEntityByException(e)
        }
        if (deletedAmount != 0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
        }
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(
                ErrorResponseEntity(
                    code = HttpStatus.NOT_FOUND.value(),
                    message = "Space marines with category $category was not found",
                )
            )
    }

    @GetMapping("loyalists")
    @CrossOrigin("*")
    fun getAnyLoyalSpaceMarine(): ResponseEntity<Any> {
        val spaceMarine: SpaceMarine?
        try {
            spaceMarine = spaceMarinesService.getAnyLoyalSpaceMarine()
        } catch (e: Exception) {
            return buildErrorResponseEntityByException(e)
        }
        return if (spaceMarine != null) {
            ResponseEntity.ok().body(spaceMarine)
        } else {
            ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                    ErrorResponseEntity(
                        code = HttpStatus.NOT_FOUND.value(),
                        message = "Верных десантников не осталось (((((((",
                    )
                )
        }
    }

    @GetMapping("amount")
    @CrossOrigin("*")
    fun getHealthySpaceMarinesAmount(
        @RequestParam("minHealth") minHealth: Float?,
    ): ResponseEntity<Any> {
        if (minHealth == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                    ErrorResponseEntity(
                        code = HttpStatus.BAD_REQUEST.value(),
                        message = "minHealth query param must be present",
                    )
                )
        }
        val amount: Long
        try {
            amount = spaceMarinesService.getAmountOfHealthySpaceMarines(minHealth)
        } catch (e: Exception) {
            return buildErrorResponseEntityByException(e)
        }
        return ResponseEntity.ok().body(AmountResponseEntity(amount))
    }
}
