package ru.itmo.spacemarinesservice.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.itmo.spacemarinesservice.model.ResponseEntity.ErrorResponseEntity
import ru.itmo.spacemarinesservice.service.StarshipsService
import ru.itmo.spacemarinesservice.util.buildErrorResponseEntityByException
import java.util.UUID

@RestController
@RequestMapping("/starships")
class StarshipsController(
    @Autowired private val starshipsService: StarshipsService
) {

    @PostMapping("/create/{starship-id}/{starship-name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun saveStarship(
        @PathVariable("starship-id") starshipId: UUID,
        @PathVariable("starship-name") starshipName: String,
    ): ResponseEntity<Any> {
        if (starshipName.isEmpty()) {
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                    ErrorResponseEntity(
                        code = HttpStatus.BAD_REQUEST.value(),
                        message = "starship name must be not empty",
                    )
                )
        }
        try {
            starshipsService.saveStarship(starshipId, starshipName)
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
        } catch (e: Exception) {
            return buildErrorResponseEntityByException(e)
        }
    }

    @PostMapping("/{starship-id}/load/{space-marine-id}")
    fun loadSpaceMarine(
        @PathVariable("starship-id") starshipId: String,
        @PathVariable("space-marine-id") spaceMarineId: Long,
    ): ResponseEntity<Any> {
        try {
            val amount = starshipsService.loadSpaceMarine(UUID.fromString(starshipId), spaceMarineId)
            if (amount == 0) {
                return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(
                        ErrorResponseEntity(
                            code = HttpStatus.NOT_FOUND.value(),
                            message = "Spacemarine with id = $spaceMarineId or Starship with id = $starshipId not found",
                        )
                    )
            }
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
        } catch (e: IllegalArgumentException) {
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                    ErrorResponseEntity(
                        code = HttpStatus.BAD_REQUEST.value(),
                        message = "Bad value of starshipId = $starshipId",
                    )
                )
        } catch (e: Exception) {
            return buildErrorResponseEntityByException(e)
        }
    }
}