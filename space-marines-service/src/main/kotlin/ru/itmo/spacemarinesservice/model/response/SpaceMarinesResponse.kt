package ru.itmo.spacemarinesservice.model.response

import ru.itmo.spacemarinesservice.model.entity.SpaceMarine

data class SpaceMarinesResponse(
    val total: Int,
    val limit: Int?,
    val offset: Int?,
    val data: List<SpaceMarine>,
)
