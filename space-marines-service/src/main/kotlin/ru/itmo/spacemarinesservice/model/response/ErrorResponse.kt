package ru.itmo.spacemarinesservice.model.response

data class ErrorResponse(
    val code: Int,
    val message: String,
)
