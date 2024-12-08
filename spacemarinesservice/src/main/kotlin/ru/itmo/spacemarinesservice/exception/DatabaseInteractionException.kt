package ru.itmo.spacemarinesservice.exception

class DatabaseInteractionException(override val message: String?, override val cause: Throwable? = null) : Exception(message, cause)