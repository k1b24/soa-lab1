package ru.itmo.spacemarinesservice.util

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import ru.itmo.spacemarinesservice.model.ResponseEntity.ErrorResponseEntity

fun buildErrorResponseEntityByException(e: Exception): ResponseEntity<Any> = ResponseEntity
    .status(HttpStatus.INTERNAL_SERVER_ERROR)
    .body(
        ErrorResponseEntity(
            code = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            message = e.message ?: "",
        )
    )
