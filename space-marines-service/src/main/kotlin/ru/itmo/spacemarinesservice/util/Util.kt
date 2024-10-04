package ru.itmo.spacemarinesservice.util

import jakarta.ws.rs.core.Response
import ru.itmo.spacemarinesservice.model.response.ErrorResponse

fun buildErrorResponseByException(e: Exception): Response = Response
    .status(Response.Status.INTERNAL_SERVER_ERROR)
    .entity(
        ErrorResponse(
            code = Response.Status.INTERNAL_SERVER_ERROR.statusCode,
            message = e.message ?: "",
        )
    )
    .build()