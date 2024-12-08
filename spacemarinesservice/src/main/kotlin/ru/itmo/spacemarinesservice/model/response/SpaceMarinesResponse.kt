package ru.itmo.spacemarinesservice.model.ResponseEntity

import ru.itmo.spacemarinesservice.model.body.SpaceMarine

data class SpaceMarinesResponseEntity(
    val total: Int,
    val limit: Int?,
    val offset: Int?,
    val data: List<SpaceMarine>,
)
