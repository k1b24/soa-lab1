package ru.itmo.spacemarinesservice.model.request

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.DecimalMax
import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import ru.itmo.spacemarinesservice.model.entity.AstartesCategory
import ru.itmo.spacemarinesservice.model.entity.SpaceMarine
import java.math.BigDecimal

data class PostSpaceMarineRequest(
    @get:Size(min = 1)
    @get:JsonProperty(required = true)
    @get:NotNull
    val name: String,
    @get:JsonProperty(required = true)
    val coordinates: Coordinates,
    @get:JsonProperty(required = false)
    @get:NotNull
    @get:DecimalMin("1")
    val health: BigDecimal,
    @get:JsonProperty(required = false)
    @get:NotNull
    val loyal: Boolean,
    @get:JsonProperty(required = false)
    @get:NotNull
    val height: Double,
    @get:JsonProperty(required = false)
    val category: AstartesCategory?,
    @get:JsonProperty(required = false)
    val chapter: Chapter,
) {
    data class Coordinates(
        @get:JsonProperty(required = true)
        @get:NotNull
        var x: Float,
        @get:JsonProperty(required = true)
        @get:DecimalMax("643")
        @get:NotNull
        var y: BigDecimal,
    )

    data class Chapter(
        @get:JsonProperty(required = true)
        @get:Size(min = 1)
        var name: String,
        @get:JsonProperty(required = true)
        var world: String?,
    )

    fun toSpaceMarineEntity(): SpaceMarine = SpaceMarine(
        name = name,
        coordinates = SpaceMarine.Coordinates(coordinates.x, coordinates.y.toDouble()),
        health = health.toFloat(),
        loyal = loyal,
        height = height,
        category = category,
        chapter = SpaceMarine.Chapter(chapter.name, chapter.world)
    )
}
