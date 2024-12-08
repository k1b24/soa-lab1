package ru.itmo.spacemarinesservice.model.ResponseEntity

data class ErrorResponseEntity(
    val code: Int,
    val message: String,
)
